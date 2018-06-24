import java.util.ArrayList;
import java.util.List;

public class RecursiveCheck extends Checker {

    public RecursiveCheck(int[] list) {
        super(list);
    }

    private boolean check(int index, int sum, List<Integer> members) {
        if (sum == 0) return true;

        if (index < 0) return false;

        boolean resultWithThisItem = sum >= list[index] && check(index - 1, sum - list[index], members);
        boolean resultWithoutThisItem = check(index -1, sum, members);

        if (resultWithThisItem) {
            members.add(list[index]);
        }
        return resultWithThisItem || resultWithoutThisItem;
    }

    public boolean solve(int sum) {
        List<Integer> items = new ArrayList<>();
        boolean result = check(this.list.length - 1, sum, items);
        System.out.println(items);
        return result;
    }
}
