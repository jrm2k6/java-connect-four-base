import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jrm2k6 on 7/6/14.
 */
public class StrategyHelper {
    public StrategyHelper()
    {

    }

    public int playNextToChipIfExists(BoardModel boardChips) {
        Chip[] lastRow = boardChips.getChips()[boardChips.getNumberRows()-1];
        for (int i=0; i<lastRow.length; i++) {
            if (lastRow[i] != null) {
                if (i == 0) {
                    return 1;
                } else if (i == lastRow.length-1) {
                    return lastRow.length-1;
                }
                else {
                    int[] candidates = new int[]{i-1, i+1};
                    int indexToChoose = new Random().nextInt(candidates.length);
                    return candidates[indexToChoose];
                }
            }
        }

        return new Random().nextInt(boardChips.getNumberColumns());
    }

    public int findMostDangerousSpotForOpponent(BoardModel boardModelClone, State state)
    {
        HashMap<Integer, Integer> mappingScoreColumn = new HashMap<Integer, Integer>();
        if (boardModelClone.getNumberChipsPlayed() < 5) {
            return new Random().nextInt(boardModelClone.getNumberRows());
        }

        for (int r=0; r<boardModelClone.getNumberRows(); r++) {
            for (int c=0; c<boardModelClone.getNumberColumns(); c++) {
                if (isMinimalDistanceRespected(boardModelClone, r, c)) {
                    Chip chip = boardModelClone.getChipAtPosition(new Point(r, c));
                    if (chip == null) {
                        Chip fakeChip = new Chip(r, c);
                        int maxScoreForChip = checkScoreIfOpponentPlaysNextTime(boardModelClone, fakeChip, state);
                        mappingScoreColumn.put(maxScoreForChip, c);
                    }
                }
            }
        }

        if (mappingScoreColumn.size() == 0) {
            return new Random().nextInt(boardModelClone.getChips().length);
        } else {
            return HashmapUtils.maxScoreInHashmap(mappingScoreColumn);
        }
    }

    public boolean isMinimalDistanceRespected(BoardModel boardModel, int r, int c) {
        return (r == 5 || boardModel.getChipAtPosition(new Point(r+1, c)) != null);
    }

    public int checkScoreIfOpponentPlaysNextTime(BoardModel boardModelClone, Chip chip, State state) {
        chip.setState(state);
        ArrayList<Chip> neighbors = boardModelClone.getNeighbors(chip);
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
