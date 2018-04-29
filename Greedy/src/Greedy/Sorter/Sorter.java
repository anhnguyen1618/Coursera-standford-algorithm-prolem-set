package Greedy.Sorter;

import Greedy.Job;

public abstract class Sorter {
    public String name;

    public abstract int compareTo(Job first, Job second);

    public static int getValue(double firstNum, double secondNum) {
        double result = firstNum - secondNum;
        if (result > 0) return 1;
        if (result == 0) return 0;
        return -1;
    }
}
