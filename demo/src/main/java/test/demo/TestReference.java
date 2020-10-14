package test.demo;

public class TestReference {

    private Object object = new Object();
    private Object object2 = object;

    private void test() {
        Object localObject = object;
        System.out.println("object:" + object);
        System.out.println("object2:" + object2);
        System.out.println("localObject:" + localObject);

        object = new Object();
        System.out.println("object:" + object);
        System.out.println("object2:" + object2);
        System.out.println("localObject:" + localObject);
    }

    public static void main(String[] args) {
        new TestReference().test();
    }

}
