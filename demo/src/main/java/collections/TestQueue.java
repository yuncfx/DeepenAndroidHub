package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class TestQueue {
    public static void main(String[] args) {

        Queue<String> myQueue = new LinkedList<String>();
        myQueue.add("A");
        myQueue.add("B");
        myQueue.add("C");
        myQueue.add("D");

        List<String> myList = new ArrayList<String>(myQueue);

        for (Object theFruit : myList)
            System.out.println(theFruit);
    }

    @Test
    public void test1() {
        Queue<String> queue = new LinkedList<String>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        queue.offer("Fourth");

        System.out.println("Size: " + queue.size());

        System.out.println("Queue head using peek   : " + queue.peek());
        System.out.println("Queue head using element: " + queue.element());

        Object data;
        while ((data = queue.poll()) != null) {
            System.out.println(data);
        }
    }

    @Test
    public void test2() {
        Queue<String> queue = new LinkedList<String>();

        queue.add("A");
        queue.add("B");

        // ���̲���һ��queue��
        // �����queue�� capacity-restricted���Ƽ�ʹ��add
        // add���������У�offer���ڶ�����
        queue.offer("C");
        queue.offer("D");

        // ��ȡ���Ƴ�ͷԪ��, �������Ϊ�գ��׳��쳣NoSuchElementException
        System.out.println("remove: " + queue.remove());
        System.out.println(queue);

        // ��ȡͷԪ�أ����Ƴ�, �������Ϊ�գ��׳��쳣NoSuchElementException
        System.out.println("element: " + queue.element());
        System.out.println(queue);

        // ��ȡ���Ƴ�ͷԪ�أ� �������Ϊ�գ�����null
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);

        // ��ȡͷԪ�أ����Ƴ����������Ϊ�գ�����null
        System.out.println("peek: " + queue.peek());
        System.out.println(queue);
    }
}
