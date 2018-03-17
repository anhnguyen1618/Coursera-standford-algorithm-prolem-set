public class RankedNode {
    public int indexToNode;
    public int distanceFromStart;

    public RankedNode(int indexToNode, int distanceFromStart) {
        this.indexToNode = indexToNode;
        this.distanceFromStart = distanceFromStart;
    }

    public boolean equals(Object o) {
        if (o instanceof RankedNode) {
            RankedNode otherNode = (RankedNode) o;
            return indexToNode == otherNode.indexToNode;
        }
        return false;
    }
}
