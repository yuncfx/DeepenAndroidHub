package simpletest;

public class TestInterface implements Inter {
	public static void main(String[] args) {
		
	}

	@Override
	public double calculate(int a) {
		return 0;
	}

}

interface Inter {
	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}