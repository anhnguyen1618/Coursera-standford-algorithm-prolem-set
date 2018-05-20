public class Main {
    public static void main(String[] args) {
        OptimalBinaryTree bt = new OptimalBinaryTreeIteractive();
        bt.nodes.add(0.05);
        bt.nodes.add(0.4);
        bt.nodes.add(0.08);
        bt.nodes.add(0.04);
        bt.nodes.add(0.1);
        bt.nodes.add(0.1);
        bt.nodes.add(0.23);
        double result = bt.solve();
        System.out.println(result);
    }
}
