package MinimumSpanningTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<Integer, Node> storage = new HashMap<>();
    private Set<Integer> visitedNodes = new HashSet<>();
    private Map<Integer, Integer> distanceLookup = new HashMap<>();
    public int lengthOfSpanningTree = 0;
    public int totalNumbOfNode = 0;

    private PriorityQueue<RankedNode> minheap = new PriorityQueue<>(1, new NodeComparator());

    private static int MAX_DISTANCE = 999999999;

    public void addNodeAndEdge(int node, int neighborNodeIndex, int weight) {

        if (storage.get(node) == null) {
            storage.put(node, new Node());
        }

        if (storage.get(neighborNodeIndex) == null) {
            storage.put(neighborNodeIndex, new Node());
        }

        storage.get(node).addEdge(neighborNodeIndex, weight);
    }

    public void print() {
        System.out.println(storage);
    }

    public void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            int[] parts = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (parts.length == 2) {
                totalNumbOfNode = parts[0];
            } else if (parts.length == 3) {
                addNodeAndEdge(parts[0], parts[1], parts[2]);
                addNodeAndEdge(parts[1], parts[0], parts[2]);
                RankedNode newNode = new RankedNode(parts[0], MAX_DISTANCE);
                if (!this.minheap.contains(newNode)) {
                    this.minheap.add(newNode);
                }

                newNode = new RankedNode(parts[1], MAX_DISTANCE);
                if (!this.minheap.contains(newNode)) {
                    this.minheap.add(newNode);
                    this.distanceLookup.put(parts[1], MAX_DISTANCE);
                }
            }
        }
    }

    public void updateDistanceNeighborNode(int nodeIndex, int newDistance) {
        if (this.distanceLookup.get(nodeIndex) < newDistance) return;
        RankedNode updatedNode = new RankedNode(nodeIndex, newDistance);
        if (this.minheap.contains(updatedNode)) {
            this.minheap.remove(updatedNode);
        }
        this.minheap.add(updatedNode);
        this.distanceLookup.put(updatedNode.indexToNode, newDistance);
    }

    public void computeMinPlanningTree(){

        while (visitedNodes.size() < totalNumbOfNode) {
            RankedNode suckedRankedNode = this.minheap.poll();

            if (suckedRankedNode.rankedDistance != MAX_DISTANCE) {
                lengthOfSpanningTree += suckedRankedNode.rankedDistance;
            }

            Node suckedNode = getNodeFromIndex(suckedRankedNode.indexToNode);
            visitedNodes.add(suckedRankedNode.indexToNode);

            for (Edge edge : suckedNode.edges) {
                int neighborIndex = edge.toNodeIndex;
                if (!visitedNodes.contains(neighborIndex)) {
                    updateDistanceNeighborNode(neighborIndex, edge.length);
                }
            }
        }

        System.out.println("Sum of minimun spanning tree is "+ this.lengthOfSpanningTree);
    }

    public Node getNodeFromIndex(int index) {
        return this.storage.get(index);
    }
}