import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inversion {

    ArrayList<Integer> array = new ArrayList<>();

    private class ReturnedData {
        ArrayList<Integer> array;
        BigInteger count;
        public ReturnedData(ArrayList<Integer> array, BigInteger count) {
            this.array = array;
            this.count = count;
        }
    }


    public void loadData(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            int element = Integer.parseInt(scan.nextLine());
            this.array.add(element);
        }
    }

    public void solve() {
        ReturnedData result = this.sortAndCount(this.array);
        System.out.println("Number of inversion is: " + result.count);
    }

    public ReturnedData sortAndCount(ArrayList<Integer> array) {
        int size = array.size();
        if (size == 1) {
            return new ReturnedData(array, new BigInteger("0"));
        }
        ReturnedData leftData = sortAndCount(new ArrayList<Integer>(array.subList(0, size/2)));

        ReturnedData rightData = sortAndCount(new ArrayList<Integer>(array.subList(size/2, size)));

        return this.mergeAndCount(size, leftData, rightData);
    }

    public ReturnedData mergeAndCount(int size, ReturnedData leftData, ReturnedData rightData) {
        ArrayList<Integer> result = new ArrayList<>();
        int count = 0;
        int j = 0, k = 0;
        for (int i = 0; i < size; i++) {

            if (j == leftData.array.size()) {
                result.add(rightData.array.get(k));
                k++;
                continue;
            }

            if (k == rightData.array.size()) {
                result.add(leftData.array.get(j));
                j++;
                continue;
            }


            int leftElement = leftData.array.get(j);
            int rightElement = rightData.array.get(k);

            if (leftElement > rightElement) {
                result.add(rightElement);
                k++;
                count += leftData.array.size() - j;
            } else {
                result.add(leftElement);
                j++;
            }
        }

        BigInteger finalResult = new BigInteger(String.valueOf(count));
        return new ReturnedData(result, finalResult.add(leftData.count).add(rightData.count));
    }


}
