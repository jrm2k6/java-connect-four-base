import java.util.EventObject;

/**
 * Created by jrm2k6 on 6/11/14.
 */
public class MoveEvent extends EventObject {
    private int columnPlayed;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MoveEvent(Object source, int columnPlayed) {
        super(source);
        this.columnPlayed = columnPlayed;
    }

    public int getColumnPlayed() {
        return this.columnPlayed;
    }
}
