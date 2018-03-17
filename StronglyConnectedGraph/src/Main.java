import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
//        load(graph, "sample.txt");
//        graph.load("test.txt", false);
//        graph.print();
        graph.computeStronglyConnected();

        System.out.println("hello");
    }


}
