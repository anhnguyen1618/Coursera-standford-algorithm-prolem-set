import java.io.FileNotFoundException;

public class KnapSackRecursive extends KnapSack {

    public void solve(String fileName) throws FileNotFoundException {
        load(fileName);
        int result = compute(0, limit);
        System.out.println("Result is "+ result);
    }

    private int compute(int index, int limit) {
        if (index == items.size() || limit <= 0) return 0;
        int cacheResult = getResultFromCache(index, limit);
        if (cacheResult > 0) return cacheResult;

        Item currentItem = items.get(index);

        int resultWithoutThisItem = compute(index + 1, limit);
        int resultWithThisItem = currentItem.value + compute(index + 1, limit - currentItem.weight);
        int finalResult = currentItem.weight > limit ? resultWithoutThisItem : Math.max(resultWithoutThisItem, resultWithThisItem);

        updateCache(index, limit, finalResult);
        return finalResult;
    }
}
