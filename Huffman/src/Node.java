public class Node {
    public String id;
    public int weight;
    public int height = 0;
    public Node leftChild, rightChild;

    public Node(String id, int weight) {
        this.id = id;
        this.weight = weight;
    }
}
