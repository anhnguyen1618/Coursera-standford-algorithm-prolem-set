import java.util.ArrayList;
import java.util.Map;

public class ThreadExecutor extends Thread{

    int index;
    int size;
    Solver solver;

    public ThreadExecutor(int index, int size, Solver solver) {
        this.index = index;
        this.size = size;
        this.solver = solver;
    }

    public void run () {
       if (this.solver != null) {
           this.solver.solve(index, size);
       }
    }
}