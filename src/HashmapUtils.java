import java.util.HashMap;
import java.util.Map;

/**
 * Created by jrm2k6 on 7/6/14.
 */
public class HashmapUtils {
    public static int maxScoreInHashmap(HashMap<Integer, Integer> mappingScoreColumn) {
        Map.Entry<Integer, Integer> maximumEntry = null;

        for (Map.Entry<Integer, Integer> item : mappingScoreColumn.entrySet()) {
            if (maximumEntry == null || item.getKey().compareTo(maximumEntry.getKey()) > 0) {
                maximumEntry = item;
            }
        }

        return maximumEntry.getValue();
    }
}
