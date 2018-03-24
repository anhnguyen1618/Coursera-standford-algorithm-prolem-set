import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static final String FILE_PATH = "data.txt";
    public static void main(String[] args) throws FileNotFoundException {
        int result = calculateMedianStreak(FILE_PATH);
        System.out.println("result is " + result);
    }

    public static int calculateMedianStreak(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        PriorityQueue<Integer> lowerHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> higherHeap = new PriorityQueue<>();
        List<Integer> medians = new ArrayList<>();

        while(scan.hasNextLine()) {
            int number = Integer.parseInt(scan.nextLine());
            add(number, lowerHeap, higherHeap);
            balance(lowerHeap, higherHeap);
            int median = getMedian(lowerHeap, higherHeap);
            System.out.println("lower: " + lowerHeap.peek() + ", higer: "+ higherHeap.peek() + ", median: " + median);
            medians.add(median);
        }

        return calculateSumModulo(medians);
    }

    public static void add(int number, PriorityQueue<Integer> lowerHeap, PriorityQueue<Integer> higherHeap) {
        if (lowerHeap.isEmpty() || number <= lowerHeap.peek()) {
            lowerHeap.add(number);
        } else {
            higherHeap.add(number);
        }
    }

    public static void balance(PriorityQueue<Integer> lowerHeap, PriorityQueue<Integer> higherHeap) {
        PriorityQueue<Integer> smaller = lowerHeap.size() < higherHeap.size() ? lowerHeap : higherHeap;
        PriorityQueue<Integer> bigger = lowerHeap.size() > higherHeap.size() ? lowerHeap : higherHeap;

        if (smaller.size() + 2 <= bigger.size()) {
            smaller.add(bigger.poll());
        }
    }

    public static int getMedian(PriorityQueue<Integer> lowerHeap, PriorityQueue<Integer> higherHeap) {
        if (lowerHeap.size() == higherHeap.size()) {
            return lowerHeap.peek();
        }

        PriorityQueue<Integer> biggerHeap = lowerHeap.size() > higherHeap.size() ? lowerHeap : higherHeap;
        return biggerHeap.peek();
    }

    public static int calculateSumModulo(List<Integer> medians) {
        int sum = medians.stream().reduce(0, (acc, cur) -> acc + cur);
        return sum % 10000;
    }
}
