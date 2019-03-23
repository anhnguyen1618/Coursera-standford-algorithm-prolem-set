import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Node[] nodes;
    public int numNode = 0;
    public int[] visited;

    private class Node {
        public double x;
        public double y;
        public Node( double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double calDistance(Node other) {
            return Math.sqrt( Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
        public String toString() {
            return "{x: " + this.x + ", " + "y:" + this.y + "}";
        }
    }

    public Graph(String fileName) throws FileNotFoundException {
        load(fileName);
    }

    private void load(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        boolean isFistLine = false;
        int counter = 0;
        while(scan.hasNextLine()) {
            if (!isFistLine) {
                isFistLine = true;
                String parts = scan.nextLine();
                this.numNode = Integer.parseInt(parts);
                this.nodes = new Node[this.numNode];
                this.visited = new int[this.numNode];
                continue;
            }

            List<Double> parts = Arrays.stream(scan.nextLine().split(" "))
                    .map(Double::parseDouble)
                    .collect(Collectors.toList());
            Node node = new Node(parts.get(1), parts.get(2));
            this.nodes[counter] = node;
            counter++;
        }


    }

    public double solve() {
        int curNode = 0;
        int vertexLeft = this.numNode;
        double result = 0;
        while (vertexLeft > 0) {
            visited[curNode] = 1;
            int localNext = curNode;
            double min = 99999999;
            for (int j = 0; j < this.numNode; j++) {
                if(curNode != j && visited[j] == 0) {
                    double distance = calDistance(curNode, j);
                    if (distance < min) {
                        localNext = j;
                        min = distance;
                    }
                }
            }
            result += vertexLeft != 1 ? min : 0;
            curNode = localNext;
            vertexLeft--;
        }

        result += calDistance(curNode, 0);

        return result;
    }

    private double calDistance(int i, int j) {
        Node first =  nodes[i];
        Node second = nodes[j];
        return first.calDistance(second);
    }

}
