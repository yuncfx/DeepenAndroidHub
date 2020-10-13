package test.shanemao.puzzler;

/**
 * @author shane
 */

public class Creator {
    /**
     * Java 语言规范不允许一个本地变量声明语句作为一条语句在 for、while 或 do
     * 循环中重复执行[JLS 14.12-14]。一个本地变量声明作为一条语句只能直接出现
     * 在一个语句块中。 （一个语句块是由一对花括号以及包含在这对花括展中的语句
     * 和声明构成的。）
     * <p>
     * 一个本地变量声明不能被用作 for、while 或 do 循环中的重复执行语句，
     * 它作为一条语句只能出现在一个语句块中。
     */
    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++)
//            Creature creature = new Creature();

        for (int i = 0; i < 100; i++) {
            new Creature();
        }
        System.out.println(Creature.numCreated());
    }
}

class Creature {
    private static long numCreated = 0;

    public Creature() {
        numCreated++;
    }

    public static long numCreated() {
        return numCreated;
    }
}
