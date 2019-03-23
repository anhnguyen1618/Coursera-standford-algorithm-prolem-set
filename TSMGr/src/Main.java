import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graph g = new Graph("data.txt");
        double result = g.solve();
        System.out.println(result);
    }
}
