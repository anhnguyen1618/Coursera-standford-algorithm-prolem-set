package Greedy;

import Greedy.Sorter.Sorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DelegatedSolver {
    public List<Job> jobs = new ArrayList<>();

    public void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            int[] elements = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (elements.length == 2) {
                jobs.add(new Job(elements[0], elements[1]));
            }
        }
    }

    public void compute(Sorter sorter) {
        Collections.sort(jobs, sorter::compareTo);

        System.out.println("Greedy result of "+ sorter.name +" is " + this.calculateCompleteTime());
    }

    public double calculateCompleteTime() {
        double baseCompletionTime = 0;
        for (Job job: jobs) {
            job.completionTime = baseCompletionTime + job.length;
            baseCompletionTime+= job.length;
        }

        return jobs.stream()
                .mapToDouble(job -> job.completionTime * job.weight)
                .sum();
    }
}
