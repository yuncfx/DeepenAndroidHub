package test.shanemao.puzzler;


class Point {
    protected final int x, y;

    /**
     * 在一个 final 类型的实例域被赋值之前，存在着取用其值的可能，
     * 而此时它包含的仍旧是其所属类型的缺省值
     * <p>
     * // Cached at construction time
     */
    private final String name;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        // 3. Invoke subclass method
        /*
         * 无论何时，只要一个构造器调用了一个已经被其子类覆写了的方法，那么该问题
         就会出现，因为以这种方式被调用的方法总是在实例被初始化之前执行。要想避
         免这个问题，就千万不要在构造器中调用可覆写的方法，直接调用或间接调用都
         不行[EJ Item 15]。这项禁令应该扩展至实例初始器和伪构造器
         （pseudoconstructors）readObject 与 clone。 （这些方法之所以被称为伪构造
         器，是因为它们可以在不调用构造器的情况下创建对象。）
         */
        name = makeName();
    }

    protected String makeName() {
        return "[" + x + "," + y + "]";
    }

    @Override
    public final String toString() {
        return name;
    }

    /*
     * 可以通过惰性初始化 name 域来订正该问题，即当它第一次被使用时初始化，
     以此取代积极初始化，即当 Point 实例被创建时初始化。
     */
/*    public final synchronized String toString2() {
        if (name == null) {
            name = makeName();
        }
        return name;
    }*/
}

/**
 * @author shane
 * puzzler51
 */
public class ColorPoint extends Point {
    private final String color;

    private ColorPoint(int x, int y, String color) {
        // 2. Chain to Point constructor
        super(x, y);
        // 5. Initialize blank final-Too late
        this.color = color;
    }

    @Override
    protected String makeName() {
        // 4. Executes before subclass constructor body!
        return super.makeName() + ":" + color;
    }

    public static void main(String[] args) {
        // 1. Invoke subclass constructor
        // print : [4,2]:null
        System.out.println(new ColorPoint(4, 2, "purple"));
    }
}
