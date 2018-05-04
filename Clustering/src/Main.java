import java.io.FileNotFoundException;

public class Main {
    public static final String FILE_NAME = "data.txt";
    public static final String FILE_NAME_2 = "data2.txt";
    public static final int NUMBER_OF_CLUSTERS = 4;

    public static void main(String[] args) throws FileNotFoundException {
        ExplicitCluster explicitCluster = new ExplicitCluster();
        explicitCluster.solve(FILE_NAME, NUMBER_OF_CLUSTERS);

        ImplicitCluster implicitCluster = new ImplicitCluster();
        implicitCluster.solve(FILE_NAME_2);
    }
}
