package kot.variance;

/**
 * @author shanemao
 */
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void toWork() {
        System.out.println("我是工人" + name + "，我要好好干活！！！");
    }
}
