import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
//        Inversion inversion = new Inversion();
//        try {
//            inversion.loadData("data.txt");
////            inversion.array.add(6);
////            inversion.array.add(5);
////            inversion.array.add(4);
////            inversion.array.add(3);
////            inversion.array.add(2);
////            inversion.array.add(1);
//
//
//            inversion.solve();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println(power(2, 6));
    }

    public static int power(int a, int b) {
        if (b ==1) {
            return a;
        }
        int result = power(a, b/2);

        if (b % 2 != 0) {
            return a * result * result;
        }

        return result * result;
    }

}
