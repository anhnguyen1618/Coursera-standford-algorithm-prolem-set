import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<Integer, Map<Integer, Double>> storage = new HashMap<>();
    private List<Node> nodes = new ArrayList<>();
    public int numNode = 0;
    private class Node {
        public double x = 0;
        public double y = 0;
        public Node( double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double calDistance(Node other) {
            return Math.sqrt( Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }

    public Graph(String fileName) throws FileNotFoundException {
        load(fileName);
    }
    public double getDistance(int i, int j) {
        return this.storage.get(i).get(j);
    }

    private void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));

        boolean isFistLine = false;
        while(scan.hasNextLine()) {
            if (!isFistLine) {
                isFistLine = true;
                String parts = scan.nextLine();
                this.numNode = Integer.parseInt(parts);
                continue;
            }

            List<Double> parts = Arrays.stream(scan.nextLine().split(" "))
                    .map(Double::parseDouble)
                    .collect(Collectors.toList());
            Node node = new Node(parts.get(0), parts.get(1));
            nodes.add(node);
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i +1; j < nodes.size(); j++) {
                addEdge(i, j);
            }
        }
    }

    private void addEdge(int i, int j) {
        storage.computeIfAbsent(i, k -> new HashMap<>());
        storage.computeIfAbsent(j, k -> new HashMap<>());
        Node first =  nodes.get(i);
        Node second = nodes.get(j);
        double distance = first.calDistance(second);
        storage.get(i).put(j, distance);
        storage.get(j).put(i, distance);
    }

}
