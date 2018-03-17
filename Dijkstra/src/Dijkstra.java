import java.io.FileNotFoundException;

public class Dijkstra {
    public final static String FILE_NAME = "test.txt";
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
        graph.load(FILE_NAME);
        graph.dijkstra(1);
//        graph.print();
        int[] keys = {7,37,59,82,99,115,133,165,188,197};
        System.out.println(graph.getResult(keys));
    }
}
