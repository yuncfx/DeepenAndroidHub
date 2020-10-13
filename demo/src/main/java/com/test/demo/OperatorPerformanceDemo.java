package com.test.demo;

import org.junit.Test;

public class OperatorPerformanceDemo {

    @Test
    public void testMultiSimple() {
        int num = 2048;
        if (num % 2== 0) {
            // n odd
        } else {
            // n even
        }

        if ((num & 1) == 1) {
            // n odd
        } else {
            // n even
        }
    }

    @Test
    public void testMulti() {
        int num = 1024;
        long l = System.currentTimeMillis();

        for (int i = 1; i < 10_0000_0000; i++) {
//            num %= i;
//            num &=i;

            if (num % 2== 0) {
                // n odd
            } else {
                // n even
            }

            if ((num & 1) == 1) {
                // n odd
            } else {
                // n even
            }
        }

        System.out.println("duration : " + (System.currentTimeMillis() - l));
    }
}
