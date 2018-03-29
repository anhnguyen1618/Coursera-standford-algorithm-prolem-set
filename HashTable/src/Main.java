import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final int MIN_VALUE = -10000;
    public static final int MAX_VALUE = 10000;
    public static final String FILE_NAME = "data.txt";
    public static int NUMBER_OF_THREAD = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Solver solver = new Solver();

        solver.load(FILE_NAME);

        solveQuizWithThreads(solver);

    }

    public static void solveQuizWithThreads(Solver solver) throws InterruptedException {
        ThreadExecutor[] threads = new ThreadExecutor[NUMBER_OF_THREAD];
        final int NUMBER_OF_INPUT_PER_THREAD = solver.inputs.size() / NUMBER_OF_THREAD;

        for (int i = 0; i < NUMBER_OF_THREAD; i++) {
            ThreadExecutor thread = new ThreadExecutor(i * NUMBER_OF_INPUT_PER_THREAD, NUMBER_OF_INPUT_PER_THREAD, solver);
            threads[i] = thread;
            thread.start();
        }

        for (ThreadExecutor thread: threads) {
            thread.join();
        }

        System.out.println("THe result is " + solver.calculateResult());
    }

}
