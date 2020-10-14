package test.refactor;

/**
 * @author shane
 */
public class Test {

    private int i;

    public Test(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

}

class InnerTest {

    public Test get(int x) {
        // 匿名内部类调用父类的构造方法
        return new Test(x) {
            @Override
            public int getI() {
                return super.getI() * 10;
            }
        };
    }
}


