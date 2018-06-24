public abstract class Checker {
    int[] list;
    public Checker(int[] list) {
        this.list = list;
    }
    public abstract boolean solve(int sum);
}
