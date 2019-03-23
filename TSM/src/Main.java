import java.io.FileNotFoundException;
import java.util.*;

public class Main {


    public static final int MAX = 99999999;
    public static final String FILE_NAME_1= "city1.txt";
    public static final String FILE_NAME_2= "city2.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph_1 = new Graph(FILE_NAME_1);
        double result_1 = solve(graph_1, 11, 12);

        Graph graph_2 = new Graph(FILE_NAME_2);
        double result_2 = solve(graph_2, 0 , 1);
        System.out.println("final result " + (result_1 + result_2));
    }


    public static double solve(Graph graph, int excludeNode1, int excludeNode2) {
        Map<Integer, ArrayList<String>> subSet = new HashMap<>();
        Map<String, double[]> storage = new HashMap<>();
        computeSubSet(graph.numNode, subSet);
        int number = graph.numNode;
        double result = MAX;

        String max = Integer.toBinaryString(1 << (number - 1));

        for (String sub: subSet.get(1)) {
            storage.computeIfAbsent(sub, k -> new double[number]);
            if (sub.equals(max)) {
                storage.get(sub)[0] = 0;
            } else {
                storage.get(sub)[0] = MAX;
            }
        }


        for (int sizeBitSet= 2; sizeBitSet<= number; sizeBitSet++) {
            for (String sub : subSet.get(sizeBitSet)) {
                if (sizeBitSet == 2) {
                    storage.computeIfAbsent(sub, k -> new double[number]);
                    for (int i = 1; i < number; i ++) {
                        storage.get(sub)[i] = graph.getDistance(i, 0);
                    }
                    continue;
                }


                for (int j = 1; j < number; j++) {
                    if (sub.charAt(j) == '1') {
                        double min = MAX;
                        for (int k = 1; k < number; k++) {
                            if (k != j && sub.charAt(k) == '1') {
                                StringBuilder subString = new StringBuilder(sub);
                                subString.setCharAt(j, '0');
                                String subSetString = subString.toString();
                                double curDistance= storage.get(subSetString)[k] + graph.getDistance(k, j);
                                min = Math.min(min, curDistance);
                            }
                        }

                        storage.computeIfAbsent(sub, k -> new double[number]);
                        storage.get(sub)[j] = min;
                    }
                }
            }
        }
        String finalSet = subSet.get(number).get(0);
        storage.computeIfAbsent(finalSet, k -> new double[number]);
        for (int j = 1; j < number; j++) {
            double curDistance = storage.get(finalSet)[j] + graph.getDistance(j, 0);
            result= Math.min(result, curDistance);
        }
        double excludeEdgeLength = graph.getDistance(excludeNode1, excludeNode2);
        return result - excludeEdgeLength;
    }

    public static void computeSubSet(int number, Map<Integer, ArrayList<String>> subSet) {
        // mask is use to ensure that all binary number has <number> digits
        int max = 1 << (number -1) ;
        // we always know that the first element in set is always 1 => only need to compute number-1 digit
        for (int i = 0; i < max; i++) {
            String bin = Integer.toBinaryString(max |i);
            int numSetBit = getSetBit(bin);
            subSet.computeIfAbsent(numSetBit, k -> new ArrayList<>());
            subSet.get(numSetBit).add(bin);
        }
    }

    public static int getSetBit(String bin) {
        int result = 0;
        for (int i = 0; i< bin.length(); i++) {
            if (bin.charAt(i) == '1') result++;
        }
        return result;
    }



}