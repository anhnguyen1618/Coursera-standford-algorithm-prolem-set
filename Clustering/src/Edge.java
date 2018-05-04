public class Edge {
    public int start, end, length;
    public Edge(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    @Override
    public String toString() {
        return this.start + " -> " + this.end + " : " + this.length;
    }
}
