package test.puzzler;

import org.junit.Test;

/**
 * @author shane
 */

public class Strange {

    @Test
    public void strange1() {
        try {
            Missing m = new Missing();
        } catch (NoClassDefFoundError ex) {
            System.out.println("Got it!");
        }
    }

    @Test
    public void strange2() {
        Missing m;
        try {
            m = new Missing();
        } catch (NoClassDefFoundError ex) {
            System.out.println("Got it!");
        }
    }

    /**
     * 总之，不要对捕获 NoClassDefFoundError 形成依赖。语言规范非常仔细地描述
     * 了类初始化是在何时发生的[JLS 12.4.1]，但是类被加载的时机却显得更加不可
     * 预测。更一般地讲，捕获 Error 及其子类型几乎是完全不恰当的。这些异常是为
     * 那些不能被恢复的错误而保留的。
     *
     * @throws Exception
     */
    @Test
    public void normal() throws Exception {
        try {
            Object m = Class.forName("Missing").
                    newInstance();
        } catch (ClassNotFoundException ex) {
            System.err.println("Got it!");
        }
    }
}

class Missing {
    Missing() {
    }
}