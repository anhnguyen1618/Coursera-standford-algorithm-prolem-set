import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
    public PriorityQueue<Node> heap = new PriorityQueue<>(1 , Comparator.comparingInt(a -> a.weight));

    public void solve(String fileName) throws FileNotFoundException {
        load(fileName);
        while (heap.size() > 1) {
            Node[] nodes =getLowestNodes();
            Node mergedNode = merge(nodes[0], nodes[1]);
            heap.add(mergedNode);
        }

        Node rootNode = heap.poll();

        System.out.println("Height is " + rootNode.height);
        System.out.println("number of bit is " + dfs(rootNode, 0));
    }

    private int dfs(Node node, int level) {
        if (node.height == 0 || node.leftChild == null || node.rightChild == null) return level;

        Node leftChild = node.leftChild;
        Node rightChild = node.rightChild;
        Node smallerNode = leftChild.height < rightChild.height ? leftChild : rightChild;

        return dfs(smallerNode, level + 1);
    }

    private Node[] getLowestNodes() {
        Node firstNode = heap.poll();
        Node secondNode = heap.poll();
        return new Node[]{firstNode, secondNode};
    }

    private Node merge(Node firstNode, Node secondNode) {
        Node mergedNode = new Node("", firstNode.weight + secondNode.weight);
        mergedNode.leftChild = firstNode;
        mergedNode.rightChild = secondNode;

        mergedNode.height = Math.max(firstNode.height, secondNode.height) + 1;
        return mergedNode;
    }



    private void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        int counter = 0;
        while(scan.hasNextLine()) {
            int number = Integer.parseInt(scan.nextLine());
            if (counter > 0) {
                heap.add(new Node(Integer.toString(counter), number));
            }
            counter++;
        }
    }
}
