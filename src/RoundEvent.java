import java.util.EventObject;

/**
 * Created by jrm2k6 on 6/11/14.
 */
public class RoundEvent extends EventObject {
    private int teamNumber;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RoundEvent(Object source, int teamNumber) {
        super(source);
        this.teamNumber = teamNumber;
    }

    public int getTeamNumber() {
        return this.teamNumber;
    }
}
