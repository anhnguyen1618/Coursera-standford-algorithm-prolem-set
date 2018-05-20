import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimalBinaryTreeRecursion extends OptimalBinaryTree {
    public double solve(int begin, int end) {
        if (begin > end) return 0;

        if (begin == end) return nodes.get(begin);

        double cachedValue = readValueFromCache(begin, end);

        if (cachedValue > 0) {
            return cachedValue;
        }

        List<Double> results = new ArrayList<>();

        double sum = sum(begin, end);
        for (int i = begin; i <= end; i++) {
            results.add(sum + solve(begin, i - 1) + solve(i + 1, end));
        }

        double value = Collections.min(results);
        updateCache(begin, end, value);
        return value;
    }
}
