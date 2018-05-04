import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExplicitCluster {
    public List<Edge> edges = new ArrayList<>();
    public UnionFind<Integer> unionFind = new UnionFind<>();

    public void solve(String fileName, int numberOfCluster) throws FileNotFoundException {
        load(fileName);
        Collections.sort(edges, Comparator.comparingInt(a -> a.length));
        int result = 0;

        for (Edge edge : this.edges) {

            boolean isBelongToSameGroup = unionFind.isBelongToSameGroup(edge.start, edge.end);

            if (isBelongToSameGroup) {
                continue;
            }

            if (unionFind.getAllGroups().size() <= numberOfCluster) {
                result = edge.length;
                break;
            }

            unionFind.merge(edge.start, edge.end);
        }

        System.out.println("result is "+ result);
    }


    public void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            int[] elements = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (elements.length == 3) {
                int startNode = elements[0];
                int endNode = elements[1];
                int length = elements[2];

                unionFind.add(startNode);
                unionFind.add(endNode);

                this.edges.add(new Edge(startNode, endNode, length));
            }
        }
    }
}
