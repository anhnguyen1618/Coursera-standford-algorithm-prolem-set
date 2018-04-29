package MinimumSpanningTree;

import java.util.ArrayList;

public class Node {
    public ArrayList<Edge> edges;
    public boolean visited = false;

    public Node() {
        this.edges = new ArrayList<>();
    }

    public void addEdge(int toNodeIndex, int length) {
        this.edges.add(new Edge(toNodeIndex, length));
    }

    public String toString() {
        String result = "{ ";
        for (Edge edge: this.edges) {
            result += edge.toNodeIndex + " : " + edge.length + ", ";
        }
        result += "}";
        return result;
    }
}