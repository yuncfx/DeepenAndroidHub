package structure._011_hash;

public class Asserts {
	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("test failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
