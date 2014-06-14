import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by jrm2k6 on 6/11/14.
 */
public class DefensiveStrategy implements ConnectFourStrategy {
    private int teamNumber;
    private BoardModel boardModel;
    private ConnectFourEventDispatcher eventDispatcher;

    public DefensiveStrategy(BoardModel boardModel, int teamNumber, ConnectFourEventDispatcher eventDispatcher) {
        this.teamNumber = teamNumber;
        this.eventDispatcher = eventDispatcher;
        this.boardModel = boardModel;
    }


    @Override
    public int getNextMove() {
        return findMostDangerousSpotForOpponent();
    }

    @Override
    public void onColumnFullEventReceived(int index) {

    }

    @Override
    public void onRoundEventReceived(RoundEvent event) {
        if (event.getTeamNumber() == this.teamNumber) {
            this.eventDispatcher.dispatchEvent(new MoveEvent(this, this.getNextMove()));
        }
    }

    public int findMostDangerousSpotForOpponent() {
        HashMap<Integer, Integer> mappingScoreColumn = new HashMap<Integer, Integer>();
        if (boardModel.getNumberChipsPlayed() < 5) {
            return new Random().nextInt(boardModel.getChips().length);
        }

        for (int r=0; r<boardModel.getChips().length; r++) {
            for (int c=0; c<boardModel.getChips()[r].length; c++) {
                if (isMinimalDistanceRespected(r,c)) {
                    Chip chip = boardModel.getChipAtPosition(new Point(r, c));
                    if (chip == null) {
                        Chip fakeChip = new Chip(r, c);
                        int maxScoreForChip = checkScoreIfOpponentPlaysNextTime(fakeChip);
                        mappingScoreColumn.put(maxScoreForChip, c);
                    }
                }
            }
        }

        if (mappingScoreColumn.size() == 0) {
            return new Random().nextInt(boardModel.getChips().length);
        } else {
            return maxScoreInHashmap(mappingScoreColumn);
        }
    }

    private int maxScoreInHashmap(HashMap<Integer, Integer> mappingScoreColumn) {
        Map.Entry<Integer, Integer> maximumEntry = null;

        for (Map.Entry<Integer, Integer> item : mappingScoreColumn.entrySet()) {
            if (maximumEntry == null || item.getKey().compareTo(maximumEntry.getKey()) > 0) {
                maximumEntry = item;
            }
        }

        return maximumEntry.getValue();
    }

    private boolean isMinimalDistanceRespected(int r, int c) {
        return (r == 5 || boardModel.getChipAtPosition(new Point(r+1, c)) != null);
    }

    private int checkScoreIfOpponentPlaysNextTime(Chip chip) {
        chip.setState(State.PLAYED_TEAM_1);
        ArrayList<Chip> neighbors = boardModel.getNeighbors(chip);
        ArrayList<Chip> clonedNeighbors = new ArrayList<Chip>();
        for (Chip neighbor : neighbors) {
            try {
                if (neighbor != null) {
                    Chip clonedNeighbor = (Chip) neighbor.clone();
                    clonedNeighbors.add(clonedNeighbor);
                } else {
                    clonedNeighbors.add(null);
                }

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return 0;
            }
        }

        chip.updateConnections(clonedNeighbors);
        int scoreHorizontal = checkDirection(chip, ChipRelationship.LEFT_NEIGHBOR, 0, chip.state) + checkDirection(chip, ChipRelationship.RIGHT_NEIGHBOR, 0, chip.state);
        int scoreVertical = checkDirection(chip, ChipRelationship.TOP_NEIGHBOR, 0, chip.state) + checkDirection(chip, ChipRelationship.BOTTOM_NEIGHBOR, 0, chip.state);
        int scoreDiagonal1 = checkDirection(chip, ChipRelationship.TOP_LEFT_NEIGHBOR, 0, chip.state) + checkDirection(chip, ChipRelationship.TOP_LEFT_NEIGHBOR.getOpposite(), 0, chip.state);
        int scoreDiagonal2 = checkDirection(chip, ChipRelationship.TOP_RIGHT_NEIGHBOR, 0, chip.state) + checkDirection(chip, ChipRelationship.TOP_RIGHT_NEIGHBOR.getOpposite(), 0, chip.state);
        return Math.max(Math.max(scoreHorizontal, scoreVertical), Math.max(scoreDiagonal1, scoreDiagonal2));
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
