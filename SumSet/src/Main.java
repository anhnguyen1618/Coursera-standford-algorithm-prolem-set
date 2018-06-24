public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        int[] list = {9,3,4,2};
        int sum = 13;
//        RecursiveCheck checker = new RecursiveCheck(list);
        Checker checker = new IterativeCheck(list);
        System.out.println(checker.solve(16));
    }
}
