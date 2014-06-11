import java.util.EventListener;

/**
 * Created by jrm2k6 on 6/11/14.
 */
public interface MoveEventListener extends EventListener {
    public void moveEventReceived(MoveEvent event);
}
