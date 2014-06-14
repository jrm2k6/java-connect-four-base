import java.awt.*;
import java.util.EventObject;

/**
 * Created by jrm2k6 on 6/11/14.
 */
public class RoundEvent extends EventObject {
    private int teamNumber;
    private Point previousMovePlayed;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RoundEvent(Object source, Point previousMovePlayed, int teamNumber) {
        super(source);
        this.previousMovePlayed = previousMovePlayed;
        this.teamNumber = teamNumber;
    }

    public int getTeamNumber() {
        return this.teamNumber;
    }
    public Point getPreviousMovePlayed() { return this.previousMovePlayed; }
}
