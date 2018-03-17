import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    private Map<Integer, Node> storage = new HashMap<>();
    private Stack<Integer> stackTrace = new Stack<>();
    public static String FILE_PATH = "sample.txt";

    public void addNodeAndNeighborNode(int node, int neighborNode) {
        if (storage.get(node) == null) {
            storage.put(node, new Node());
        }
        if (storage.get(neighborNode) == null) {
            storage.put(neighborNode, new Node());
        }
        storage.get(node).addNeigbor(neighborNode);
    }

    public void print() {
        System.out.println(storage);
    }

    public void load(String fileName, boolean isReversed) throws FileNotFoundException {
        this.storage = new HashMap<>();
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            String[] parts = scan.nextLine().split(" ");
            int hostNode = isReversed? Integer.parseInt(parts[1]): Integer.parseInt(parts[0]);
            int neighborNode =  isReversed? Integer.parseInt(parts[0]): Integer.parseInt(parts[1]);
            this.addNodeAndNeighborNode(hostNode, neighborNode);
        }
    }

    public void explore(int startNode) {
        Node node = this.storage.get(startNode);
        if (node.visited) {
            return;
        }

        node.visited = true;
        for (int neighborNode: node.neighborVertices) {
            this.explore(neighborNode);
        }

        this.stackTrace.push(startNode);
    }

    public void reverseExplore(int startNode, ArrayList<Integer> group) {
        Node node = this.storage.get(startNode);
        if (node == null || node.visited) {
            return;
        }

        node.visited = true;
        group.add(startNode);
        for (int neighborNode: node.neighborVertices) {
            this.reverseExplore(neighborNode, group);
        }
    }

    public void computeStronglyConnected() throws FileNotFoundException {
        ArrayList<Integer> sizes = new ArrayList<>();

        this.load(FILE_PATH, false);
        Set<Integer> keys = this.storage.keySet();
        for (int node : keys) {
            this.explore(node);
        }

        this.load(FILE_PATH, true);

        while (!this.stackTrace.empty()) {
            int nodeIndex = this.stackTrace.pop();
            ArrayList<Integer> group = new ArrayList<>();
            this.reverseExplore(nodeIndex, group);
            sizes.add(group.size());
        }

        Collections.sort(sizes, Collections.reverseOrder());

        for (int i = 0; i < 5; i++) {
            System.out.println(sizes.get(i));
        }

    }


}