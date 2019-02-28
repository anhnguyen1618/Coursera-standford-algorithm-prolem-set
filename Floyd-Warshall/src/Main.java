
import java.io.FileNotFoundException;

public class Main {
    public static final String FILE_NAME_1 = "g1.txt";
    public static final String FILE_NAME_2 = "g2.txt";
    public static final String FILE_NAME_3 = "g3.txt";
    public static void main(String[] args) throws FileNotFoundException {
        Solver solver = new Solver();
        System.out.println("The result is: "+ solver.solve());
    }
}
