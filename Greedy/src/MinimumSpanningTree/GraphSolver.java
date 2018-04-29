package MinimumSpanningTree;

import java.io.FileNotFoundException;

public class GraphSolver {
    public void solve(String fileName) throws FileNotFoundException {
        Graph graph = new Graph();
        graph.load(fileName);
        graph.computeMinPlanningTree();
    }
}
