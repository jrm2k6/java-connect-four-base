/**
 * Created by jrm2k6 on 6/10/14.
 */
public class Opponent implements ColumnFullEventListener{
    private final ConnectFourStrategy strategy;

    public Opponent(ConnectFourStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void columnFullEventReceived(ColumnFullEvent event) {
        this.strategy.onColumnFullEventReceived(event.getIndexColumn());
    }
}
