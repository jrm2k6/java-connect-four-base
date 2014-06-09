import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jrm2k6 on 6/9/14.
 */
public class Chip {
    public int x;
    public int y;
    public Chip[] connections = new Chip[8];
    public State state;

    public Chip(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.state = State.NOT_PLAYED;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void updateConnections(ArrayList<Chip> neighbors) {
        for (int i=0; i<ChipRelationship.values().length; i++) {
            updateConnection(neighbors.get(i), i, true);
        }
    }

    public void updateConnection(Chip chip, int index, boolean crossUpdate) {
        this.connections[index] = chip;

        // we also update the opposite relationship of our new neighbor
        // this should be done somewhere else but..
        if (chip != null && crossUpdate) {
            chip.updateConnection(this, ((index+4)%8), false);
        }
    }
}
