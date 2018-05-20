import java.util.*;

public abstract class OptimalBinaryTree {
    public Map<Integer, HashMap<Integer, Double>> cache = new HashMap<>();
    public List<Double> nodes = new ArrayList<>();

    public double solve() {
        return this.solve(0, nodes.size() - 1);
    }

    public abstract double solve(int begin, int end);

    public void updateCache(int begin, int end, double value) {
        if (cache.get(begin) == null) {
            cache.put(begin, new HashMap<>());
        }

        cache.get(begin).put(end, value);
    }

    public double readValueFromCache(int begin, int end) {
        if (cache.get(begin) == null || cache.get(begin).get(end) == null) return 0;

        return cache.get(begin).get(end);
    }
    
    public double sum(int begin, int end) {
        double result = 0;
        for (int i = begin; i <= end; i++) {
            result += nodes.get(i);
        }

        return result;
    }
}
