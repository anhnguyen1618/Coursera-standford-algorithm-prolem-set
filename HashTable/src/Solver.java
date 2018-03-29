import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solver {
    public Map<Long, Boolean> inputs = new HashMap<>();
    public Map<Long, Boolean> results = new HashMap<>();
    public ArrayList<Long> list = new ArrayList<>();

    public void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            long number = Long.parseLong(scan.nextLine());
            list.add(number);
            inputs.put(number, true);
        }

    }

    public boolean searchTheRemnaint(long number, long sum) {
        if (sum == number *2) return false;
        return inputs.get(sum-number) != null;
    }

    public int calculateResult() {
        return results.keySet().size();
    }

    public void solve(int index, int size) {
        for (int i = index; i < index + size ; i++ ) {
            long number = this.list.get(i);
            for (long sum = Main.MIN_VALUE; sum <= Main.MAX_VALUE; sum++) {
                if (results.get(sum) == null && this.searchTheRemnaint(number, sum)) {
                    results.put(sum, true);
                }
            }
        }
    }
}
