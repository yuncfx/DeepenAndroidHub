package test.shanemao.puzzler;

/**
 * @author shane
 */
public class MyLinkedList<E> {
    private Node<E> head = null;

    /**
     * 一个泛型类的内部类可以访问到它
     * 的外围类的类型参数。而编程者的意图很明显,即一个 Node 的类型参数应该和
     * 它外围的 LinkedList 类的类型参数一样,所以 Node 完全不需要有自己的类型参
     * 数。
     */
//    private class Node<E> {
//        E value;
//        Node<E> next;
//
//        // Node constructor links the node as a new head
//        Node(E value) {
//            this.value = value;
//            // incompatible types
//            this.next = head;
//            head = this;
//        }
//    }

//    private class Node {
//        E value;
//        Node next;
//
//        //Node 的构造器,将 node 链接到链表上作为新的表头
//        Node(E value) {
//            this.value = value;
//            this.next = head;
//            head = this;
//        }
//    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(E e) {
        head = new Node<>(e, head);
// Link node as new head
    }

    public void dump() {
        for (Node<E> n = head; n != null; n = n.next) {
            System.out.println(n.value + " ");
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("world");
        list.add("Hello");
        list.dump();
    }
}
