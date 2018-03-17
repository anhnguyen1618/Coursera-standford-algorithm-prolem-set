import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    public ArrayList<Integer> neighborVertices;
    public boolean visited = false;

    public Node() {
        this.neighborVertices = new ArrayList<>();
    }

    public void addNeigbor(int neighborIndex) {
        this.neighborVertices.add(neighborIndex);
    }

    public String toString() {
        String result = "{ ";
        for (int node: this.neighborVertices) {
            result += node + " ";
        }
        result += "}";
        return result;
    }
}
