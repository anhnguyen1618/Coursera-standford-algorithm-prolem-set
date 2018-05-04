import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ImplicitCluster {
    public Set<String> nodes = new HashSet<>();
    public UnionFind<String> unionFind = new UnionFind<>();
    
    public void solve(String fileName) throws FileNotFoundException {
        load(fileName);

        for (String node: nodes) {
            List<String> possibleNodes = StringGenerator.generateAllPossibleValue(node);
            
            for (String possibleNode: possibleNodes) {
                if (nodes.contains(possibleNode)) {
                    unionFind.merge(possibleNode, node);
                }
            }
        }
        System.out.println("the number remaining group is " + this.unionFind.getAllGroups().keySet().size());
    }

    public void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            String[] elements = scan.nextLine().split(" ");

            if (elements.length > 3) {
                String node = String.join("", elements);
                if (!this.nodes.contains(node)) {
                    this.nodes.add(node);
                    this.unionFind.add(node);
                }
            }
        }
    }

}
