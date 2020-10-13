package simpletest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaAPITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String aaa = "Ora_Android_V0.1.45_v2";
		String bbb = "Ora_Android_V0.1.48_IDC_Test";
		String a = aaa.substring(aaa.lastIndexOf("_V") + 2);
		String b = bbb.substring(bbb.lastIndexOf("_V") + 2);
		System.out.print(a);
		System.out.print("\n=========================\n");
		System.out.print(b);
		System.out.print("\n=========================\n");
		System.out.print(b.compareTo(a) > 0);
		System.out.print("\n=========================\n\n");

		String c = string2Unicode("壪");
		System.out.print(c);
		System.out.print("\n=========================");
		setString("a", "b", "c", "d", "e");
		System.out.print("\n=========================");
		setString();

		threadExecutor();
	}

	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	public static void setString(String... strings) {
		int aa = strings.length;
		String bb = "";
		for (int i = 0; i < aa; i++) {
			bb += strings[i];
		}

		System.out.print("\n==================   bb== " + bb);
		System.out.print("\n=========================\n");
	}

	public static void threadExecutor() {

		FileOperating mfileOperating = new FileOperating();
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newCachedThreadPool();

		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口

		MyThread t1 = new MyThread(mfileOperating);

		MyInputThread t2 = new MyInputThread(/*new Runnable() {
			
			@Override
			public void run() {
				System.out.println("hello world ");
				
			}
		}*/ mfileOperating);
		
		
		MyInputThread t3 = new MyInputThread(/*new Runnable() {
				
				@Override
				public void run() {
					System.out.println("hello world ");
					
				}
			}*/ mfileOperating);


		// 将线程放入池中进行执行

		pool.execute(t1);

		pool.execute(t2);
		
		pool.execute(t3);

		// 关闭线程池

		pool.shutdown();

	}
}
