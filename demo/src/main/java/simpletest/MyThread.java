package simpletest;

public class MyThread extends Thread {

	private FileOperating mFileOperating;

	MyThread(FileOperating fileOperating) {
		super();
		this.mFileOperating = fileOperating;
	}

	@Override
	public void run() {
		while (true) {
			int value = mFileOperating.getValue();
			System.out.println(Thread.currentThread().getName() + "正在执行。。。");
			System.out.println("value  = " + value);
		}
		// System. out.println(Thread. currentThread().getId()+ "正在执行。。。");

	}

}