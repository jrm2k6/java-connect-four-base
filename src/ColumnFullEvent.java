import java.util.EventObject;

/**
 * Created by jrm2k6 on 6/10/14.
 */
public class ColumnFullEvent extends EventObject {
    private final int indexColumn;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ColumnFullEvent(Object source, int indexColumn) {
        super(source);
        this.indexColumn = indexColumn;
    }

    public int getIndexColumn() {
        return indexColumn;
    }
}
