import java.util.EventListener;
import java.util.EventObject;

/**
 * Created by jrm2k6 on 6/10/14.
 */
public interface EventDispatcher {
    public void addEventListener(EventListener listener);
    public void removeEventListener(EventListener listener);
    public void dispatchEvent(EventObject event);
}
