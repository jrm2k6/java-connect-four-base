import java.util.*;

/**
 * Created by jrm2k6 on 6/10/14.
 */
public class ColumnFullEventDispatcher implements EventDispatcher<ColumnFullEvent> {
    private HashMap<String, EventListener> listeners;

    public ColumnFullEventDispatcher()
    {
        this.listeners = new HashMap<String, EventListener>();
    }

    @Override
    public void addEventListener(EventListener listener) {
        System.out.println("ColumnFullEventDispatcher.addEventListener "+ listener.getClass().getName());
        this.listeners.put(listener.getClass().getName(), listener);
    }

    @Override
    public void removeEventListener(EventListener listener) {
        this.listeners.remove(listener.getClass(), listener);
    }

    @Override
    public void dispatchEvent(ColumnFullEvent columnFullEvent) {
        Iterator it = this.listeners.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            EventListener target = (EventListener) pairs.getValue();

            if (Arrays.asList(target.getClass().getInterfaces()).contains(ColumnFullEventListener.class)) {
                ((ColumnFullEventListener) target).columnFullEventReceived(columnFullEvent);
            }
        }
    }
}
