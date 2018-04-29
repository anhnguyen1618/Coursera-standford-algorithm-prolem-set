package Greedy.Sorter;

import Greedy.Job;

public class SubtractSorter extends Sorter {
    public SubtractSorter() {
        this.name = "subtract";
    }

    @Override
    public int compareTo(Job first, Job second) {
        double scoreOfFirst = first.weight - first.length;
        double scoreOfSecond = second.weight - second.length;

        if (scoreOfFirst == scoreOfSecond) {
            return getValue(second.weight, first.weight);
        }

        return getValue(scoreOfSecond, scoreOfFirst);
    }
}
