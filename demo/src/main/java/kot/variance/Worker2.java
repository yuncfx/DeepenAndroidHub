package kot.variance;

public class Worker2 extends Person {

    public Worker2(String name, int age) {
        super(name, age);
    }

    @Override
    public void toWork() {
        System.out.println("我是2工人" + getName() + "，我也要好好干活！！！");
    }
}
