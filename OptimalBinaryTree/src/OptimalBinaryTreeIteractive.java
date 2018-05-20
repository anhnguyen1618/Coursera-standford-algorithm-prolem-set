import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimalBinaryTreeIteractive extends OptimalBinaryTree{
    @Override
    public double solve(int begin, int end) {
        for (int size = 1; size <= end - begin +1; size++) {
            for (int startIndex = 0; startIndex <= end; startIndex++) {
                int endIndex = startIndex + size - 1;
                if (endIndex > end) {
                    break;
                }

                if (size == 1) {
                    updateCache(startIndex, endIndex, nodes.get(startIndex));
                    continue;
                }

                double sum = sum(startIndex, endIndex);

                List<Double> results = new ArrayList<>();
                for (int i = startIndex; i <= endIndex; i++) {
                    results.add(readValueFromCache(startIndex, i - 1) + readValueFromCache(i+1, endIndex));
                }

                updateCache(startIndex, endIndex, sum + Collections.min(results));
            }
        }

        return readValueFromCache(begin, end);
    }
}
