import java.awt.*;

/**
 * Created by jrm2k6 on 6/9/14.
 */
public class ChipRelationShipHelper {
    public static Point getPositionToCheck(Chip chip, ChipRelationship relationship) {
        Point position;
        switch (relationship) {
            case LEFT_NEIGHBOR:
                position = new Point(chip.x, chip.y-1);
                break;
            case RIGHT_NEIGHBOR:
                position = new Point(chip.x, chip.y+1);
                break;
            case TOP_NEIGHBOR:
                position = new Point(chip.x-1, chip.y);
                break;
            case BOTTOM_NEIGHBOR:
                position = new Point(chip.x+1, chip.y);
                break;
            case TOP_LEFT_NEIGHBOR:
                position = new Point(chip.x-1, chip.y-1);
                break;
            case TOP_RIGHT_NEIGHBOR:
                position = new Point(chip.x-1, chip.y+1);
                break;
            case BOTTOM_LEFT_NEIGHBOR:
                position = new Point(chip.x+1, chip.y-1);
                break;
            case BOTTOM_RIGHT_NEIGHBOR:
                position = new Point(chip.x+1, chip.y+1);
                break;
            default:
                position = new Point(-1, -1);
                break;
        }

        return position;
    }
}
