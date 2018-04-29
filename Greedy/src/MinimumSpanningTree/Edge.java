package MinimumSpanningTree;

public class Edge {
    public int toNodeIndex;
    public int length;
    public Edge(int toNodeIndex, int length) {
        this.toNodeIndex = toNodeIndex;
        this.length = length;
    }
}