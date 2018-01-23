import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Inversion inversion = new Inversion();
        try {
            inversion.loadData("data.txt");
//            inversion.array.add(6);
//            inversion.array.add(5);
//            inversion.array.add(4);
//            inversion.array.add(3);
//            inversion.array.add(2);
//            inversion.array.add(1);


            inversion.solve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
