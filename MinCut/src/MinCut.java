import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MinCut {
    public static int NUMBER_OF_THREAD = Runtime.getRuntime().availableProcessors();
    public static String FILE_PATH = "sample.txt";
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Graph graph = new Graph();
        load(graph, FILE_PATH);

        ThreadExecutor[] threads = new ThreadExecutor[NUMBER_OF_THREAD];

        for (int i = 0; i < NUMBER_OF_THREAD; i++) {
            ThreadExecutor thread = new ThreadExecutor(graph);
            threads[i] = thread;
            thread.start();
        }

        for (ThreadExecutor thread: threads) {
            thread.join();
        }

        int minCut = threads[0].minCut;
        for (ThreadExecutor thread: threads) {
            if (thread.minCut < minCut) {
                minCut = thread.minCut;
            }
        }
        System.out.println("The number of minimun cuts is "+ minCut);

    }

    public static void load(Graph graph, String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            String[] parts = scan.nextLine().split("\t");
            int hostNode = Integer.parseInt(parts[0]);
            graph.addNode(hostNode);
            for (int i = 1; i < parts.length; i++) {
                graph.addEdges(hostNode, Integer.parseInt(parts[i]));
            }
        }
    }
}
