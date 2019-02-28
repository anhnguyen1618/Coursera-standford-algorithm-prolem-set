import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    public static final int CURRENT_NODE_INDEX = 0;
    public static final int NEIGHBOR_INDEX = 1;
    public static final int WEIGHT_INDEX = 2;

    public int nodeNum = 0;
    private Map<Integer, Map<Integer, Integer>> storage = new HashMap<>();

    public Graph(String fileName) throws FileNotFoundException {
        load(fileName);
    }

    private void addNodeAndEdge(int node, int neighborNodeIndex, int weight) {
        storage.computeIfAbsent(node, k -> new HashMap<>());

        storage.get(node).put(neighborNodeIndex, weight);
    }

    private void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        boolean isFistLine = false;
        while(scan.hasNextLine()) {
            if (!isFistLine) {
                isFistLine = true;
                String[] parts = scan.nextLine().split(" ");
                this.nodeNum = Integer.parseInt(parts[0]);
                continue;
            }

            List<Integer> parts = Arrays.stream(scan.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            addNodeAndEdge(parts.get(CURRENT_NODE_INDEX), parts.get(NEIGHBOR_INDEX), parts.get(WEIGHT_INDEX));
        }
    }

    boolean checkEdgeExist(int i, int j) {
        Map<Integer, Integer> neighBors = this.storage.get(i);
        if (neighBors != null) {
            return neighBors.containsKey(j);
        }

        return false;
    }

    int getWeight(int i, int j) {
        Map<Integer, Integer> neighBors = this.storage.get(i);
        if (neighBors != null) {
            return neighBors.get(j);
        }

        return Integer.MAX_VALUE;
    }

    public void print() {
        for (int key : this.storage.keySet()) {
            System.out.print(key + ": ");
            Map<Integer, Integer> neighBor =this.storage.get(key);
            neighBor.keySet().forEach(node -> System.out.print("{" + node + ": " + neighBor.get(node) + "} "));
            System.out.println();
        }
    }

}
