public class Item {
    public int weight, value;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{ value: " + value + ", weight: " + weight + " }" ;
    }
}
