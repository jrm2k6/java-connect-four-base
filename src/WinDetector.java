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
        return false;
    }
}
