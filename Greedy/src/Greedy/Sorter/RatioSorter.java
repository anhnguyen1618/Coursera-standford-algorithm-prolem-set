package Greedy.Sorter;

import Greedy.Job;

public class RatioSorter extends Sorter {
    public RatioSorter() {
        this.name = "ratio";
    }

    @Override
    public int compareTo(Job first, Job second) {
        double scoreOfFirst = first.weight / first.length;
        double scoreOfSecond = second.weight / second.length;

        return getValue(scoreOfSecond, scoreOfFirst);
    }
}
