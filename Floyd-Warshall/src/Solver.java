import java.io.FileNotFoundException;

public class Solver {
    public static final int MAX_VALUE = 999999999;
    private boolean hasNegLoop = false;
    private int minLength = MAX_VALUE;
    private int[][] weightTable;

    public String solve() throws FileNotFoundException {
        String[] files = {Main.FILE_NAME_1, Main.FILE_NAME_2, Main.FILE_NAME_3};
        boolean hasResult = false;

        for (String file: files) {
            Graph graph = new Graph(file);
            solve(graph);
            hasResult = hasResult || this.hasNegLoop;
        }

        if (!hasResult) return "NULL";

        return String.format("%d", minLength);
    }

    public void solve(Graph graph) {
        int minLength = MAX_VALUE;
        this.weightTable = new int[graph.nodeNum + 1][graph.nodeNum + 1];
        for (int k = 0; k <= graph.nodeNum; k++) {
            for (int i = 1; i <= graph.nodeNum; i++) {
                for (int j = 1; j <= graph.nodeNum; j++) {
                    if (k == 0) {
                        int weight = MAX_VALUE;
                        if (i == j) {
                            weight = 0;
                        } else if (graph.checkEdgeExist(i, j)) {
                            weight = graph.getWeight(i, j);
                        }
                        weightTable[i][j] = weight;
                    } else {
                        int min = Math.min(weightTable[i][j], weightTable[i][k] + weightTable[k][j]);
                        weightTable[i][j] = min;
                        if (k == graph.nodeNum && min < minLength && i != j) {
                            minLength = min;
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= graph.nodeNum; i++) {
            if (weightTable[i][i] < 0) {
                this.hasNegLoop = true;
                return;
            }
        }
        this.minLength = minLength < this.minLength ? minLength: this.minLength;
    }
}
