import java.util.*;

public class UnionFind<T> {
    private Map<T, T> masterTable = new HashMap<>();
    private Map<T, List<T>> groups = new HashMap<>();


    public void add (T node) {
        if (this.masterTable.get(node) == null) {
            this.masterTable.put(node, node);
            this.groups.put(node, new ArrayList<T>(Arrays.asList(node)));
        }
    }

    public boolean isBelongToSameGroup(T firstNode, T secondNode) {
        return this.masterTable.get(firstNode).equals(this.masterTable.get(secondNode));
    }

    public T findGroup(T node) {
        return this.masterTable.get(node);
    }

    public void merge(T firstNode, T secondNode) {
        T firstMaster = masterTable.get(firstNode);
        T secondMaster = masterTable.get(secondNode);

        if (firstMaster.equals(secondMaster)) {
            return ;
        }

        if (this.groups.get(firstMaster) == null || this.groups.get(secondMaster) == null) {
            System.out.println("Master node does not exist in Union");
            return;
        }

        T winnerMaster = this.groups.get(firstMaster).size() > this.groups.get(secondMaster).size() ? firstMaster : secondMaster;
        T loserMaster = this.groups.get(firstMaster).size() > this.groups.get(secondMaster).size() ? secondMaster : firstMaster;

        List<T> loserGroup = this.groups.get(loserMaster);

        for (T loserNode: loserGroup) {
            this.masterTable.put(loserNode, winnerMaster);
            this.groups.get(winnerMaster).add(loserNode);
        }

        this.groups.remove(loserMaster);
    }

    public Map<T, List<T>> getAllGroups() {
        return this.groups;
    }
}
