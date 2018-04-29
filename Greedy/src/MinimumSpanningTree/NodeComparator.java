package MinimumSpanningTree;

import MinimumSpanningTree.RankedNode;

import java.util.Comparator;

public class NodeComparator implements Comparator<RankedNode> {
    @Override
    public int compare(RankedNode o1, RankedNode o2) {
        return o1.rankedDistance - o2.rankedDistance;
    }
}