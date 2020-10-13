package test.shanemao.puzzler;

/**
 * @author shane
 */

public class Confusing {
    /**
     * 要想用一个 null 参数来调用 Confusing(Object)构造器，你需要这样写代码：
     * new Confusing((Object)null)
     */
    private Confusing(Object o) {
        System.out.println("Object");
    }

    private Confusing(double[] dArray) {
        System.out.println("double array");
    }

    public static void main(String[] args) {
        /*
         * Java 的重载解析过程是以两阶段运行的。第一阶段选取所有可获得并且可应用
         的方法或构造器。第二阶段在第一阶段选取的方法或构造器中选取最精确的一
         个。如果一个方法或构造器可以接受传递给另一个方法或构造器的任何参数，那
         么我们就说第一个方法比第二个方法缺乏精确性
         */
        new Confusing(null);
    }
}
