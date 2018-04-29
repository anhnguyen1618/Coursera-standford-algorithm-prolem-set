package Greedy;

public class Job{
    public double length, weight, completionTime;

    public Job(double weight, double length) {
        this.length = length;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.weight + " : " + this.length;
    }
}
