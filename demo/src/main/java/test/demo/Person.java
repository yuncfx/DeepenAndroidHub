package test.demo;

public class Person implements Cloneable {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
