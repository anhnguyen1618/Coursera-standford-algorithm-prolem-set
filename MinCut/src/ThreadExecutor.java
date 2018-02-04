import java.io.FileNotFoundException;

public class ThreadExecutor extends Thread{
    public int minCut = 99999999;
    private Graph graph;

    public ThreadExecutor(Graph originalGraph) {
        this.graph = originalGraph;
    }

    public void run () {
        for (int i = 0; i < 5000; i++) {
            Graph clonedGraph = graph.clone();
            int currentMin = clonedGraph.computeMinCut();
            minCut = currentMin< minCut ? currentMin : minCut;
        }
    }
}
