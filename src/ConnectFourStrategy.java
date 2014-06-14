/**
 * Created by jrm2k6 on 6/10/14.
 */
public interface ConnectFourStrategy {
    public int getNextMove();
    public void onColumnFullEventReceived(int index);
    public void onRoundEventReceived(RoundEvent event);
}
