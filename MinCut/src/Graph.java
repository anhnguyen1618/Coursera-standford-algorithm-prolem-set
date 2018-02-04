import java.util.*;

public class Graph {
    private Map<Integer, HashMap<Integer, Integer>> storage = new HashMap<>();

    public Graph() {}

    public Graph(Map<Integer, HashMap<Integer, Integer>> storage) {
        this.storage = storage;
    }

    public void addNode(int node) {
        storage.put(node, new HashMap<>());
    }

    public void addEdges(int node, int neighborNode) {
        HashMap<Integer, Integer> edges = storage.get(node);
        edges.put(neighborNode, 1);
    }

    public void fuse(int hostNode, int fusedNode) {
        HashMap<Integer, Integer> neighborNodeOfFusedNode = storage.get(fusedNode);
        for (int neighborNode: neighborNodeOfFusedNode.keySet()) {
            if (neighborNode != hostNode) {
                storage.get(neighborNode).remove(fusedNode);
                int currentValue = 0;

                if (storage.get(hostNode) != null && storage.get(hostNode).get(neighborNode) != null) {
                    currentValue = storage.get(hostNode).get(neighborNode);
                }

                int remainingLinkFusedNode = storage.get(fusedNode).get(neighborNode);
                int newConnectionValue = currentValue + remainingLinkFusedNode;

                // Sync connection value between hostNode and neihborNode
                storage.get(hostNode).put(neighborNode, newConnectionValue);
                storage.get(neighborNode).put(hostNode, newConnectionValue);
            }
        }

        storage.get(hostNode).remove(fusedNode);
        storage.remove(fusedNode);
    }

    public void print() {
        System.out.println(storage);
    }

    public int computeMinCut() {
        while (storage.keySet().size() > 2) {
            Random rand = new Random();
            List<Integer> nodes = new ArrayList<>(storage.keySet());
            rand.nextInt(nodes.size());
            int hostNode = nodes.get(rand.nextInt(nodes.size()));

            List<Integer> neighborVertices = new ArrayList<>(storage.get(hostNode).keySet());

            int fusedNode = neighborVertices.get(rand.nextInt(neighborVertices.size()));
            this.fuse(hostNode, fusedNode);
        }

        List<Integer> remainingNodes = new ArrayList<>(storage.keySet());

        int minCut = storage.get(remainingNodes.get(0)).get(remainingNodes.get(1));

        return minCut;
    }

    public Graph clone() {
        Map<Integer, HashMap<Integer, Integer>> newStorage = new HashMap<>();
        for (int key : storage.keySet()) {
            newStorage.put(key, new HashMap<>());
            for (int deepKey: storage.get(key).keySet()) {
                newStorage.get(key).put(deepKey, storage.get(key).get(deepKey));
            }
        }
        return new Graph(newStorage);
    }


}
