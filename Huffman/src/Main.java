import java.io.FileNotFoundException;

public class Main {
    public static String FILE_NAME = "data2.txt";
    public static void main(String[] args) throws FileNotFoundException {
//        Huffman hm = new Huffman();
//        hm.solve(FILE_NAME);
        IndependentSet is = new IndependentSet();
        is.compute(FILE_NAME);
    }
}
