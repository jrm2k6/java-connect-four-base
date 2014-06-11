import java.util.*;

/**
 * Created by jrm2k6 on 6/10/14.
 */
public class ConnectFourEventDispatcher implements EventDispatcher {
    private HashMap<String, EventListener> listeners;

    public ConnectFourEventDispatcher()
    {
        this.listeners = new HashMap<String, EventListener>();
    }

    @Override
    public void addEventListener(EventListener listener) {
        System.out.println("ConnectFourEventDispatcher.addEventListener "+ listener.getClass().getName());
        this.listeners.put(listener.getClass().getName(), listener);
    }

    @Override
    public void removeEventListener(EventListener listener) {
        this.listeners.remove(listener.getClass(), listener);
    }

    @Override
    public void dispatchEvent(EventObject eventObject) {
        Iterator it = this.listeners.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            EventListener target = (EventListener) pairs.getValue();
            List<Class<?>> interfaces = Arrays.asList(target.getClass().getInterfaces());
            if (interfaces.contains(ColumnFullEventListener.class) && eventObject.getClass() == ColumnFullEvent.class) {
                ((ColumnFullEventListener) target).columnFullEventReceived((ColumnFullEvent) eventObject);
            } else if (interfaces.contains(MoveEventListener.class) && eventObject.getClass() == MoveEvent.class) {
                ((MoveEventListener) target).moveEventReceived((MoveEvent) eventObject);
            } else if (interfaces.contains(RoundEventListener.class) && eventObject.getClass() == RoundEvent.class) {
                ((RoundEventListener) target).roundEventReceived((RoundEvent) eventObject);
            }
        }
    }
}
