package MinimumSpanningTree;

public class RankedNode {
    public int indexToNode;
    public int rankedDistance;

    public RankedNode(int indexToNode, int rankedDistance) {
        this.indexToNode = indexToNode;
        this.rankedDistance = rankedDistance;
    }

    @Override
    public String toString() {
        return "{ "+this.indexToNode+ ": "+ this.rankedDistance+ "}";
    }

    public boolean equals(Object o) {
        if (o instanceof RankedNode) {
            RankedNode otherNode = (RankedNode) o;
            return indexToNode == otherNode.indexToNode;
        }
        return false;
    }
}