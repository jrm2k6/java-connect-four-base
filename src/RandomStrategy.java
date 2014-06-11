import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jrm2k6 on 6/10/14.
 */
public class RandomStrategy implements ConnectFourStrategy {
    private final ConnectFourEventDispatcher eventDispatcher;
    private ArrayList<Integer> availableColumns;
    private int teamNumber;

    public RandomStrategy(int nbColumns, int teamNumber, ConnectFourEventDispatcher eventDispatcher) {

        this.availableColumns = new ArrayList<Integer>();
        for (int i=0; i<nbColumns; i++) {
            this.availableColumns.add(i);
        }

        this.teamNumber = teamNumber;
        this.eventDispatcher = eventDispatcher;
    }

    public void onColumnFullEventReceived(int columnIndex) {
        if (availableColumns.contains(new Integer(columnIndex))) {
            availableColumns.remove(new Integer(columnIndex));
        }
    }

    @Override
    public void onRoundEventReceived(int teamNumber) {
        if (teamNumber == this.teamNumber) {
            this.eventDispatcher.dispatchEvent(new MoveEvent(this, this.getNextMove()));
        }
    }

    private int getRandomColumn() {
        Random randomGenerator = new Random();
        int size = availableColumns.size();
        if (size == 1) {
            return availableColumns.get(0);
        } else {
            int index = randomGenerator.nextInt(availableColumns.size()-1);
            return availableColumns.get(index);
        }
    }

    @Override
    public int getNextMove() {
        return getRandomColumn();
    }
}
