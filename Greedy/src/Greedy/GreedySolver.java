package Greedy;

import Greedy.Sorter.RatioSorter;
import Greedy.Sorter.Sorter;
import Greedy.Sorter.SubtractSorter;

import java.io.FileNotFoundException;

public class GreedySolver {
    public void solve(String fileName) throws FileNotFoundException {
        DelegatedSolver solver = new DelegatedSolver();
        solver.load(fileName);

        Sorter sorter = new SubtractSorter();
        solver.compute(sorter);

        sorter = new RatioSorter();
        solver.compute(sorter);
    }
}
