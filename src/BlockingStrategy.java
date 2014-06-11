/**
 * Created by jrm2k6 on 6/11/14.
 */
public class BlockingStrategy implements ConnectFourStrategy {
    private int teamNumber;
    private ConnectFourEventDispatcher eventDispatcher;

    public BlockingStrategy(int teamNumber, ConnectFourEventDispatcher eventDispatcher) {
        this.teamNumber = teamNumber;
        this.eventDispatcher = eventDispatcher;
    }


    @Override
    public int getNextMove() {
        return 0;
    }

    @Override
    public void onColumnFullEventReceived(int index) {

    }

    @Override
    public void onRoundEventReceived(int teamNumber) {
        if (teamNumber == this.teamNumber) {
            this.eventDispatcher.dispatchEvent(new MoveEvent(this, this.getNextMove()));
        }
    }
}
