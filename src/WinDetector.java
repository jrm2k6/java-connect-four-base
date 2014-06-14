/**
 * Created by jrm2k6 on 6/9/14.
 */
public class WinDetector {
    private BoardModel boardModel;

    public WinDetector(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public boolean checkIfWinner(Chip chip) {
        if (boardModel.getNumberChipsPlayed() < 8) {
            return false;
        }

        for (int i=0; i<ChipRelationship.values().length/2; i++) {
            ChipRelationship t = ChipRelationship.values()[i];
            ChipRelationship opposite = t.getOpposite();
            int nbInARowCurrentDirection = checkDirection(chip, t, 0, chip.state);
            int nbInARowOppositeDirection = checkDirection(chip, opposite, 0, chip.state);
            int nbInARow = nbInARowCurrentDirection + nbInARowOppositeDirection;
            if (nbInARowCurrentDirection == 4 || nbInARowOppositeDirection == 4 ||nbInARow > 4) {
                return true;
            }
        }

        return false;
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
