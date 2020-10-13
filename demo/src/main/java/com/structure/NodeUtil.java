package com.structure;

public class NodeUtil {
    private static Node head = new Node();

    public static void addNode(int data) {
        Node node = new Node(data);

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
    }

    public static void traverse(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }
    }

    public static void insertNode(Node head, int index, int value) {
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("error position");
            return;
        }

        Node temp = head;
        int currentPos = 0;
        Node newNode = new Node(value);
        while (temp.next != null) {
            if (index - 1 == currentPos) {
                newNode.next = temp.next;
                temp.next = newNode;
                break;
            }
            currentPos++;
            temp = temp.next;
        }
    }

    public static void deleteNode(Node head, int index) {
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("remove node at invalid index");
            return;
        }

        Node temp = head;
        int currentPos = 0;
        while (temp.next != null) {
            if (currentPos == index - 1) {
                Node deleteNode = temp.next;
                temp.next = deleteNode.next;
                deleteNode = null;
                return;
            }

            currentPos++;
            temp = temp.next;
        }
    }

    private static int linkListLength(Node head) {
        Node temp = head.next;
        int size = 0;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    // error
    public static Node sortLinkList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node p = null;

        boolean isExchanged = true;
        while (isExchanged && p != head.next) {

            Node q = head;
            isExchanged = false;
            for (; q.next != null && q.next != p; q = q.next) {
                System.out.println("inner q:" + q + ", q.next:" + q.next + ", p:" + p);
                if (q.data > q.next.data) {
                    int temp = q.data;
                    q.data = q.next.data;
                    q.next.data = temp;
                    isExchanged = true;
                }
            }
            p = q;
            System.out.println("p:" + p );
        }
        return head;
    }

    public static Node findKNode(Node head, int k) {
        if (k < 1 || k > linkListLength(head)) {
            return null;
        }

        Node p1 = head;
        Node p2 = head;
        for (int i = 0; i <= k - 1; i++) {
            p2 = p2.next;
        }

        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        return p1;
    }

    public static Node searchMid(Node head) {
        Node p1 = head;
        Node p2 = head;

        while (p2 != null && p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
    }

    public static void printListReversely(Node node) {
        if (node != null) {
            printListReversely(node.next);
            System.out.print(node.data + ", ");
        }
    }

    public static Node reverseList(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        addNode(1);
        addNode(2);
        addNode(6);
        addNode(7);
        addNode(8);
        addNode(3);
        addNode(4);
        addNode(5);

//        System.out.println("printListReversely");
//        printListReversely(head);
//        System.out.println("\ntraverse");
//        traverse(head);
//        System.out.println("\ninsertNode");
//        insertNode(head, 7, 33);
//        traverse(head);
//        System.out.println("\ndeleteNode");
//        deleteNode(head, 7);
//        traverse(head);
//        System.out.println("\n findKNode");
//        Node kNode = findKNode(head, 3);
//        System.out.println("kNode:" + kNode);
//        System.out.println("searchMid");
//        Node node = searchMid(head);
//        System.out.println("Mid node:" + node);
//
//        System.out.println("reverse:");
//        Node reverseHead = reverseList(head);
//        traverse(reverseHead);

        System.out.println("sort");
        Node node = sortLinkList(head);
        traverse(node);
    }
}
