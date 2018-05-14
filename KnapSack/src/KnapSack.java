import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KnapSack {
    public List<Item> items = new ArrayList<>();
    public int limit = 0;
    public HashMap<Integer, HashMap<Integer, Integer>> cache = new HashMap<>();

    public void solve(String fileName) throws FileNotFoundException {
        load(fileName);
        int result = evaluate(limit);
        System.out.println("Result is "+ result);
    }

    protected void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        int counter = 0;
        while(scan.hasNextLine()) {
            int[] elements = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (counter == 0) {
                limit = elements[0];
            } else {
                items.add(new Item(elements[0], elements[1]));
            }

            counter++;
        }
    }

    protected int evaluate(int limit) {
        for (int index = 0; index < items.size(); index++) {
            for (int currentLimit = 0; currentLimit <= limit; currentLimit++) {
                Item currentItem = items.get(index);

                int valueWithoutThisItem = getResultFromCache(index-1, currentLimit);
                int valueWithThisItem = currentItem.value + getResultFromCache(index-1, currentLimit - currentItem
                        .weight);


                int currentValue = currentLimit >= currentItem.weight
                        ? Math.max(valueWithoutThisItem, valueWithThisItem)
                        : valueWithoutThisItem;

                updateCache(index, currentLimit, currentValue);
            }
        }
        return getResultFromCache(items.size() - 1, limit);
    }

    protected void updateCache(int index,int limit, Integer finalResult) {
        if(cache.get(index) == null) {
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(limit, finalResult);
            cache.put(index, temp);
            return;
        }

        cache.get(index).put(limit, finalResult);
    }

    protected int getResultFromCache(int index, int limit) {
        if (cache.get(index) == null || cache.get(index).get(limit) == null) return 0;

        return cache.get(index).get(limit);
    }
}
