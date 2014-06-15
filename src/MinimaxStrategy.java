import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by jrm2k6 on 6/15/14.
 */
public class MinimaxStrategy implements ConnectFourStrategy {

    private ConnectFourEventDispatcher eventDispatcher;
    private BoardModel boardChips;
    private int teamNumber;

    public MinimaxStrategy(BoardModel boardChips, int teamNumber, ConnectFourEventDispatcher eventDispatcher) {
        this.boardChips = boardChips;
        this.teamNumber = teamNumber;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public int getNextMove() {
        HashMap<Integer, Integer> columnWithScore = new HashMap<Integer, Integer>();
            ArrayList<Integer> availableColumns = getAvailableColumns(boardChips);
            for (int columnToPlay : availableColumns) {
                BoardModel clonedBoard =  cloneBoardChips();
                System.out.println(boardChips.getNumberChipsPlayed());
                Point scoreForColumn = runMinimax(clonedBoard, 8, true, columnToPlay);
                columnWithScore.put(scoreForColumn.x, scoreForColumn.y);
            }

        ArrayList<Integer> candidates = filterColumnWithScoresByMaximumScore(columnWithScore);
        if (candidates.size() == 1) {
            return candidates.get(0);
        } else {
            int randomIndex = new Random().nextInt(candidates.size());
            return candidates.get(randomIndex);
        }
    }

    private ArrayList<Integer> filterColumnWithScoresByMaximumScore(HashMap<Integer, Integer> columnWithScore) {
        ArrayList<Integer> candidates = new ArrayList<Integer>();
        Map.Entry<Integer, Integer> maximumEntry = null;

        for (Map.Entry<Integer, Integer> item : columnWithScore.entrySet()) {
            if (maximumEntry == null) {
                maximumEntry = item;
                candidates.add(maximumEntry.getKey());
            } else if (item.getValue().compareTo(maximumEntry.getValue()) > 0) {
                maximumEntry = item;
                candidates.clear();
                candidates.add(maximumEntry.getKey());
            } else if (item.getValue().compareTo(maximumEntry.getValue()) == 0){
                candidates.add(item.getKey());
            }
        }

        return candidates;
    }

    private BoardModel cloneBoardChips() {
        BoardModel cloneModel = new BoardModel(boardChips.getNumberRows(), boardChips.getNumberColumns());
        Chip[][] copyChips = new Chip[boardChips.getNumberRows()][boardChips.getNumberColumns()];
        for (int i=0; i<boardChips.getNumberRows(); i++) {
            for (int j=0; j<boardChips.getNumberRows(); j++) {
                Chip originalChip = boardChips.getChipAtPosition(new Point(i, j));
                if (originalChip == null) {
                    copyChips[i][j] = null;
                } else {
                    copyChips[i][j] = new Chip(originalChip.x, originalChip.y);
                    copyChips[i][j].setState(originalChip.state);
                }
            }
        }

        return cloneModel;
    }

    @Override
    public void onColumnFullEventReceived(int index) {

    }

    @Override
    public void onRoundEventReceived(RoundEvent event) {
        if (event.getTeamNumber() == this.teamNumber) {
            int nextMove = this.getNextMove();
            this.eventDispatcher.dispatchEvent(new MoveEvent(this, nextMove));
        }
    }

    private Point runMinimax(BoardModel boardModel, int depth, boolean maximizingPlayer, int columnPlayed) {
        if (depth == 0) {
            return calculateScore(columnPlayed, boardModel);
        } else {
            int teamNumberPlaying = (maximizingPlayer) ? this.teamNumber : this.teamNumber - 1;
            Chip newChip = boardModel.addChip(columnPlayed, teamNumberPlaying-1);
            if (newChip != null) {
                boardModel.updateConnections(newChip);
            }
            return runMinimax(boardModel, depth-1, !maximizingPlayer, columnPlayed);
        }
    }

    private Point calculateScore(int columnPlayed, BoardModel boardModel) {
        int SCORE_WINNING = Integer.MAX_VALUE;
        int SCORE_LOSING = Integer.MIN_VALUE;
        int SCORE_3_IN_ROW = 1000;
        int SCORE_2_IN_ROW = 200;

        State stateChipComputer = State.PLAYED_TEAM_2;
        State stateChipOpponent = State.PLAYED_TEAM_1;

        int nbWinningSituations = findNumberChipsInARowFor(4, boardModel, stateChipComputer);
        int nbTripleInARow = findNumberChipsInARowFor(3, boardModel, stateChipComputer);
        int nbDoubleInARow = findNumberChipsInARowFor(2, boardModel, stateChipComputer);

        int nbLosingSituations = findNumberChipsInARowFor(4, boardModel, stateChipOpponent);
        int nbTripleInARowOpponent = findNumberChipsInARowFor(3, boardModel, stateChipOpponent);
        int nbDoubleInARowOpponent = findNumberChipsInARowFor(2, boardModel, stateChipOpponent);

        return new Point(columnPlayed, (nbWinningSituations * SCORE_WINNING +
                nbLosingSituations * SCORE_LOSING +
                nbTripleInARow * SCORE_3_IN_ROW +
                nbTripleInARowOpponent * -SCORE_3_IN_ROW +
                nbDoubleInARow * SCORE_2_IN_ROW +
                nbDoubleInARowOpponent * -SCORE_2_IN_ROW));
    }

    private int findNumberChipsInARowFor(int nbInARow, BoardModel boardModel, State state) {
        int nbInARowForChipFound = 0;
        for (int i=0; i<boardModel.getNumberRows(); i++) {
            for (int j=0; j<boardModel.getNumberColumns(); j++) {
                Chip chipToCheck = boardModel.getChipAtPosition(new Point(i, j));
                if (chipToCheck != null && chipToCheck.state == state) {
                    nbInARowForChipFound = nbInARowForChipFound + checkAllDirections(nbInARow, chipToCheck, state);
                }
            }
        }

        return nbInARowForChipFound;
    }

    private int checkAllDirections(int nbInARowToCheck, Chip chipToCheck, State state) {
        int result = 0;
        for (int i=0; i<ChipRelationship.values().length/2; i++) {
            ChipRelationship t = ChipRelationship.values()[i];
            ChipRelationship opposite = t.getOpposite();
            int nbInARowCurrentDirection = checkDirection(chipToCheck, t, 0, state);
            int nbInARowOppositeDirection = checkDirection(chipToCheck, opposite, 0, state);
            int nbInARow = nbInARowCurrentDirection + nbInARowOppositeDirection;
            if (nbInARowCurrentDirection == nbInARowToCheck || nbInARowOppositeDirection == nbInARowToCheck || nbInARow > nbInARowToCheck) {
                result++;
            }
        }

        return result;
    }

    private ArrayList<Integer> getAvailableColumns(BoardModel boardModel) {
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int i=0; i<boardModel.getNumberColumns(); i++) {
            if (!boardModel.isColumnFull(i)) {
                results.add(i);
            }
        }

        return results;
    }

    public int checkDirection(Chip chip, ChipRelationship direction, int nbSameColorInARow, State initialState) {
        if (chip == null || chip.state != initialState) {
            return nbSameColorInARow;
        } else {
            Chip neighbor = chip.connections[direction.ordinal()];
            return checkDirection(neighbor, direction, nbSameColorInARow + 1, initialState);
        }
    }
}
