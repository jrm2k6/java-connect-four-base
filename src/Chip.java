import java.awt.*;

/**
 * Created by jrm2k6 on 6/9/14.
 */
public class Chip {
    public int x;
    public int y;
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
}
