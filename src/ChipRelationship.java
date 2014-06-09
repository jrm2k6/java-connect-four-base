/**
 * Created by jrm2k6 on 6/9/14.
 */
public enum ChipRelationship {
    // Do not modify the order if you want to be able to rely
    // on ChipRelationship.values()[index] to access elements
    TOP_NEIGHBOR,
    TOP_RIGHT_NEIGHBOR,
    RIGHT_NEIGHBOR,
    BOTTOM_RIGHT_NEIGHBOR,
    BOTTOM_NEIGHBOR,
    BOTTOM_LEFT_NEIGHBOR,
    LEFT_NEIGHBOR,
    TOP_LEFT_NEIGHBOR;

    private ChipRelationship opposite;

    static {
        LEFT_NEIGHBOR.opposite = RIGHT_NEIGHBOR;
        RIGHT_NEIGHBOR.opposite = LEFT_NEIGHBOR;
        TOP_NEIGHBOR.opposite = BOTTOM_NEIGHBOR;
        BOTTOM_NEIGHBOR.opposite = TOP_NEIGHBOR;
        TOP_LEFT_NEIGHBOR.opposite = BOTTOM_RIGHT_NEIGHBOR;
        BOTTOM_RIGHT_NEIGHBOR.opposite = TOP_LEFT_NEIGHBOR;
        BOTTOM_LEFT_NEIGHBOR.opposite = TOP_RIGHT_NEIGHBOR;
        TOP_RIGHT_NEIGHBOR.opposite = BOTTOM_LEFT_NEIGHBOR;
    }

    public ChipRelationship getOpposite() {
        return this.opposite;
    }
}
