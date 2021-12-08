package kot.variance;

public class Worker1 extends Person {
    public Worker1(String name, int age) {
        super(name, age);
    }

    @Override
    public void toWork() {
        System.out.println("我是1工人" + getName() + "，我要好好干活！！！");
    }
}
