package simpletest;

import org.junit.Test;

/**
 * @author shane
 */

public class TestOperate {
    @Test
    public void test() {
        System.out.println(-536870912 & -536870913);

        System.out.println(-1 << 29);
    }
}
