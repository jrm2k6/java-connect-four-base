import java.util.EventListener;

/**
 * Created by jrm2k6 on 6/10/14.
 */
public interface ColumnFullEventListener extends EventListener {
    public void columnFullEventReceived(ColumnFullEvent event);
}
