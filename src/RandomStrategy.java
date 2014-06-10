import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jrm2k6 on 6/10/14.
 */
public class RandomStrategy implements ConnectFourStrategy {
    private ArrayList<Integer> availableColumns;

    public RandomStrategy(int nbColumns) {

        this.availableColumns = new ArrayList<Integer>();
        for (int i=0; i<nbColumns; i++) {
            this.availableColumns.add(i);
        }
    }

    public void onColumnFullEventReceived(int columnIndex) {
        if (availableColumns.contains(new Integer(columnIndex))) {
            availableColumns.remove(new Integer(columnIndex));
        }
    }

    public int getRandomColumn() {
        Random randomGenerator = new Random();
        int size = availableColumns.size();
        if (size == 1) {
            return availableColumns.get(0);
        } else {
            int index = randomGenerator.nextInt(availableColumns.size()-1);
            return availableColumns.get(index);
        }
    }

    public int getNextMove() {
        return getRandomColumn();
    }
}
