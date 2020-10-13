package simpletest;

public class StrategyTest {
	public static void main(String[] args) {
		String exp = "2+8";
		ICalculator cal = new Plus();
		int result = cal.calculate(exp);
		System.out.println(result);
	}
}

interface ICalculator {
	public int calculate(String exp);
}

class Minus extends AbstractCalculator implements ICalculator {
	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp, "-");
		return arrayInt[0] - arrayInt[1];
	}
}

class Plus extends AbstractCalculator implements ICalculator {
	@Override
	public int calculate(String exp) {
		// + ��������ʽ�������⺬�壬���Բ���ֱ����"+"
		int arrayInt[] = split(exp, "\\+");
		return arrayInt[0] + arrayInt[1];
	}
}

class AbstractCalculator {
	public int[] split(String exp, String opt) {
		String array[] = exp.split(opt);
		int arrayInt[] = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}
}
