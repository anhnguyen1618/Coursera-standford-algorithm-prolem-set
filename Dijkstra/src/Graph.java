import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<Integer, Node> storage = new HashMap<>();
    private Map<Integer, Integer> distanceFromStart = new HashMap<>();
    private Map<Integer, Integer> previousNodes = new HashMap<>();

    private PriorityQueue<RankedNode> minheap = new PriorityQueue<RankedNode>(1, new NodeComparator());

    private static int MAX_DISTANCE = 999999999;

    public void addNodeAndEdge(int node, int neighborNodeIndex, int weight) {

        if (storage.get(node) == null) {
            storage.put(node, new Node());
        }

        if (storage.get(neighborNodeIndex) == null) {
            storage.put(neighborNodeIndex, new Node());
        }

        distanceFromStart.put(node, MAX_DISTANCE);
        storage.get(node).addEdge(neighborNodeIndex, weight);
    }

    public void print() {
        System.out.println(storage);
    }

    public void load(String fileName) throws FileNotFoundException {
        this.storage = new HashMap<>();
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            String[] parts = scan.nextLine().split("\t");
            int hostVertice = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                List<Integer> nodeAndEdge = Arrays.stream(parts[i].split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                this.addNodeAndEdge(hostVertice, nodeAndEdge.get(0), nodeAndEdge.get(1));
            }
        }
    }

    public void dijkstra(int start){
        distanceFromStart.put(start, 0);
        this.minheap.add(new RankedNode(start, 0));

        while(!this.minheap.isEmpty()) {
            RankedNode hostNode = this.minheap.poll();
            int nodeIndex = hostNode.indexToNode;
            Node currentNode = getNodeFromIndex(nodeIndex);

            currentNode.visited = true;

            int distanceFromStartOfHostNode = this.distanceFromStart.get(nodeIndex);

            for (Edge edge: currentNode.edges) {
                int neighborIndex = edge.toNodeIndex;
                Node neighborNode = getNodeFromIndex(edge.toNodeIndex);

                this.updateNodeDistanceRecord(neighborIndex, distanceFromStartOfHostNode, edge.length);

                if (neighborNode.visited) {
                    continue;
                }

                RankedNode updatedNode = new RankedNode(neighborIndex, this.distanceFromStart.get(neighborIndex));
                if (this.minheap.contains(updatedNode)) {
                    this.minheap.remove(updatedNode);
                }

                this.minheap.add(updatedNode);
            }
        }
    }

    public Node getNodeFromIndex(int index) {
        return this.storage.get(index);
    }

    public void updateNodeDistanceRecord(int nodeIndex, int distanceFromStartOfHostNode, int edgeLength) {
        int currentDistanceFromStart = this.distanceFromStart.get(nodeIndex);
        int newDistanceFromStart = distanceFromStartOfHostNode + edgeLength;
        this.distanceFromStart.put(nodeIndex, Math.min(currentDistanceFromStart, newDistanceFromStart));
    }

    public List<Integer> getResult(int[] keys) {
        List<Integer> result = new ArrayList<>();
        for(int key: keys) {
            result.add(this.distanceFromStart.get(key));
        }
        return result;
    }
}
