import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jrm2k6 on 6/8/14.
 */
public class BoardModel {
    private Chip[][] chips;
    private int numberRows;
    private int numberColumns;
    private int nbChipsPlayed = 0;

    public BoardModel(int numberRows, int numberColumns)
    {
        this.numberRows = numberRows;
        this.numberColumns = numberColumns;
        this.chips = new Chip[numberRows][numberColumns];
    }

    public void addChip(Chip chip, int teamNumber) {
        Point position = new Point(chip.x, chip.y);
        if (chips[position.x][position.y] == null) {
            chips[chip.x][chip.y] = chip;
            // retrieves the value in enum State from index
            chip.setState(State.values()[teamNumber]);
            this.nbChipsPlayed++;
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
        for (int i=this.numberRows -1; i>=0; i--) {
            if (chips[i][columnClicked] == null) {
                return new Point(i, columnClicked);
            }
        }

        return new Point(-1, -1);
    }

    public int getNumberChipsPlayed() {
        return nbChipsPlayed;
    }

    public void updateConnections(Chip newChip) {
        ArrayList<Chip> neighbors = getNeighbors(newChip);
        newChip.updateConnections(neighbors);
    }

    public ArrayList<Chip> getNeighbors(Chip chip) {
        ArrayList<Chip> neighbors = new ArrayList<Chip>();
        // going clockwise - top - top right - right - .... - top left
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.TOP_NEIGHBOR)));
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.TOP_RIGHT_NEIGHBOR)));
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.RIGHT_NEIGHBOR)));
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.BOTTOM_RIGHT_NEIGHBOR)));
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.BOTTOM_NEIGHBOR)));
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.BOTTOM_LEFT_NEIGHBOR)));
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.LEFT_NEIGHBOR)));
        neighbors.add(getChipAtPosition(ChipRelationShipHelper.getPositionToCheck(chip, ChipRelationship.TOP_LEFT_NEIGHBOR)));

        return neighbors;
    }

    public Chip getChipAtPosition(Point position) {
        if (inBounds(position)) {
            return chips[position.x][position.y];
        } else {
            return null;
        }
    }

    private boolean inBounds(Point position) {
        return position.x < numberRows
                && position.y < numberColumns
                && position.x > -1
                && position.y > -1;
    }

    public Chip[][] getChips() {
        return chips;
    }
}
