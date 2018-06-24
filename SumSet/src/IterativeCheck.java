import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IterativeCheck extends Checker {
    private HashMap<Integer, HashMap<Integer, Boolean>> cache = new HashMap<>();
    public IterativeCheck(int[] list) {
        super(list);
    }

    @Override
    public boolean solve(int sum) {
        for (int i = 0; i < list.length; i++) {
            for (int limit = 1; limit <= sum; limit++) {
                boolean resultWithItem = limit >= list[i] && getCache(i - 1, limit - list[i]);
                boolean resultWihoutItem = getCache(i - 1, limit);
                updateCache(i, limit, resultWithItem || resultWihoutItem);
            }
        }
        boolean result = getCache(list.length - 1, sum);
        if (result) {
            System.out.println(traceItems(sum));
        }
        return result;
    }

    private boolean getCache(int i, int limit) {
        if (limit == 0) return true;
        if (i < 0) return false;

        return this.cache.get(i).get(limit);
    }

    private void updateCache(int index, int limit, boolean value) {
        this.cache.computeIfAbsent(index, k -> new HashMap<>());
        this.cache.get(index).put(limit, value);
    }

    private List<Integer> traceItems(int sum) {
        int remain = sum;
        int i = list.length - 1;
        List<Integer> results = new ArrayList<>();
        while (remain > 0 && i >= 0) {

            if (remain == list[i]) {
                results.add(list[i]);
                break;
            }

            boolean resultWithItem = remain > list[i] && getCache(i - 1, remain - list[i]);
            if (resultWithItem) {
                results.add(list[i]);
                remain -= list[i];
            }
            i--;
        }

        return  results;
    }
}
