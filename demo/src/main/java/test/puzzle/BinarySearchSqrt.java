package test.puzzle;

/**
 * 二分法开根号
 */
public class BinarySearchSqrt {

    private static final double offset = 0.0001;

    public static void main(String[] args) {
        BinarySearchSqrt searchSqrt = new BinarySearchSqrt();
        double value = searchSqrt.sqrt(1024.33);
        System.out.println(value);
        System.out.println(value * value);
    }

    private double sqrt(double n) {
        if (n < 0) {
            throw new IllegalArgumentException("invalid number, " + n + " < 0");
        }

        double min, max, mid;
        min = 0;
        max = n;
        mid = n / 2;

        while( mid * mid > n + offset || mid * mid < n - offset) {
            if (mid * mid > n + offset) {
                max = mid;
            }

            if (mid * mid < n - offset) {
                min = mid;
            }

            mid = (max + min)/2;
        }

        return mid;
    }

}
