import Greedy.GreedySolver;
import MinimumSpanningTree.GraphSolver;

import java.io.FileNotFoundException;

public class Main {
    public static final String FILE_NAME_GREEDY = "data.txt";
    public static final String FILE_NAME_GRAPH = "test-min.txt";
    public static void main(String[] args) throws FileNotFoundException {
        GreedySolver greedySolver = new GreedySolver();
        greedySolver.solve(FILE_NAME_GREEDY);

        GraphSolver solver = new GraphSolver();
        solver.solve(FILE_NAME_GRAPH);
    }
}
