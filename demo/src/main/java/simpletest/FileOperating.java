package simpletest;

import java.util.ArrayList;
import java.util.List;

public class FileOperating {

	private int mValue = 0;

	static final int MAX_ITEM = 5;

	private static List<Integer> values = new ArrayList<>(MAX_ITEM);

	synchronized void setValue(int value) {
		if (values.size() == MAX_ITEM) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}
		mValue = (int) (Math.random() * 10);
		values.add(mValue);

		System.out.println("Put: " + mValue);
		notifyAll();
		System.out.println(Thread.currentThread().getName() + "正在执行。。。");

	}

	synchronized int getValue() {
		if (values.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}

		mValue = values.remove(0);
		System.out.println("Got: " + mValue);
		notifyAll();
		System.out.println(Thread.currentThread().getName() + "正在执行。。。");
		return mValue;

	}
}