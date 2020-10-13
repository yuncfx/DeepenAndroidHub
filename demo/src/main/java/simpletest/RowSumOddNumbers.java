package simpletest;

import static org.junit.Assert.*;

import org.junit.Test;

public class RowSumOddNumbers {
    public static int rowSumOddNumbers(int n) {

        int _sum = getSum(n - 1);

        System.out.println("sum = " + _sum + ", n = " + n);
        return _sum * n + getAddition(n);

    }

    private static int getSum(int num) {
        if (num < 1) {
            return 1;
        }
        return 2 * num + getSum(num - 1);
    }

    private static int getAddition(int num) {
        if (num <= 1) {
            return 0;
        }
        return 2 * (num - 1) + getAddition(num - 1);
    }

    @Test
    public void test1() {
        assertEquals(1, RowSumOddNumbers.rowSumOddNumbers(1));
        assertEquals(74088, RowSumOddNumbers.rowSumOddNumbers(42));
    }
}
