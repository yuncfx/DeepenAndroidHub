package structure;

public class Node {
    public int data;

    public Node next;

    public Node() {}

    public Node(int data) {
        this.data = data;
    }

    public Node(Node next, int data) {
        this.next = next;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node data:" + data;
    }
}
