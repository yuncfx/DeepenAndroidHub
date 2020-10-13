package simpletest;


public class MyInputThread extends Thread {

	private FileOperating mFileOperating;

	MyInputThread(FileOperating fileOperating) {

		super();
		this.mFileOperating = fileOperating;
	}
	
	MyInputThread(Runnable runnable) {

		super(runnable);
		
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			if (mFileOperating != null) {
				mFileOperating.setValue(i++);
			}
			System.out.println("写入文件值 i= " + i);
		}
		// System. out.println(Thread. currentThread().getId()+ "正在执行。。。");
	}

}