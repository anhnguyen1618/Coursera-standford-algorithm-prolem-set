import java.io.FileNotFoundException;

public class Main {
    public final static String FILE_NAME = "data_small.txt";
    public final static String FILE_NAME_2 = "data_big.txt";
    public static void main(String[] args) throws FileNotFoundException {
        KnapSack ks = new KnapSack();
        ks.solve(FILE_NAME);

        ks = new KnapSackRecursive();
        ks.solve(FILE_NAME_2);
    }
}
