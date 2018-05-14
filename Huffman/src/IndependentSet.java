import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class IndependentSet {
    public List<Integer> vertices = new ArrayList<>();
    public HashMap<Integer, Integer> cache = new HashMap<>();

    public Set<Integer> includedVertices = new HashSet<>();

    public void compute(String fileName) throws FileNotFoundException {
        load(fileName);
        cache.put(-1, 0);
        cache.put(0, 0);
        cache.put(1, vertices.get(0));

        for (int i = 1; i < vertices.size(); i++) {
            int finalOrder = i + 1;
            int currentVertex = vertices.get(i);

            cache.put(
                finalOrder,
                Math.max(
                    cache.get(finalOrder - 2) + currentVertex, cache.get(finalOrder - 1)
                )
            );
        }

        trace();
        int[] queries = {1, 2, 3, 4, 17, 117, 517,997};
        System.out.println(produceStringResult(queries));
    }

    private String produceStringResult(int[] queries) {
        String result = "";
        for (int query: queries) {
            result += includedVertices.contains(query) ? "1": "0";
        }
        return result;
    }

    private void trace() {
        int index = vertices.size();
        while (index > 0) {
            if (cache.get(index - 2) + vertices.get(index -1) <= cache.get(index - 1)) {
                index--;
            } else {
                includedVertices.add(index);
                index -= 2;
            }
        }
    }

    private void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        int counter = 0;
        while(scan.hasNextLine()) {
            int number = Integer.parseInt(scan.nextLine());
            if (counter > 0) {
                vertices.add(number);
            }
            counter++;
        }
    }
}
