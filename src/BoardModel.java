import java.awt.*;

/**
 * Created by jrm2k6 on 6/8/14.
 */
public class BoardModel {
    private Chip[][] chips;
    private int dimension;

    public BoardModel(int dimension)
    {
        this.dimension = dimension;
        this.chips = new Chip[dimension][dimension];
    }

    public void addChip(Chip chip, int teamNumber) {
        Point position = new Point(chip.x, chip.y);
        if (chips[position.x][position.y] == null) {
            chips[chip.x][chip.y] = chip;
            // retrieves the value in enum State from index
            chip.setState(State.values()[teamNumber]);
        } else {
            try {
                throw new Exception("Spot already taken by a chip");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Point findSpot(int columnClicked)
    {
        for (int i=this.dimension-1; i>=0; i--) {
            if (chips[i][columnClicked] == null) {
                return new Point(i, columnClicked);
            }
        }

        return new Point(-1, -1);
    }
}
