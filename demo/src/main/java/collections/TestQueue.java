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

        // 立刻插入一个queue，
        // 如果该queue是 capacity-restricted，推荐使用add
        // add用在链表中，offer用在队列中
        queue.offer("C");
        queue.offer("D");

        // 获取并移除头元素, 如果队列为空，抛出异常NoSuchElementException
        System.out.println("remove: " + queue.remove());
        System.out.println(queue);

        // 获取头元素，不移除, 如果队列为空，抛出异常NoSuchElementException
        System.out.println("element: " + queue.element());
        System.out.println(queue);

        // 获取并移除头元素， 如果队列为空，返回null
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);

        // 获取头元素，不移除，如果队列为空，返回null
        System.out.println("peek: " + queue.peek());
        System.out.println(queue);
    }
}
