package simpletest;

import collections.Person;

public class GenericTest {
    public static void main(String[] args) {
        Person<String> name = new Person<>("hello");
        Person<Integer> age = new Person<>(12);
        System.out.println("name class: " + name.getClass().getName()); // month_08.Person
        System.out.println("age class: " + age.getClass().getName()); // month_08.Person
        System.out.println(name.getClass() == age.getClass()); // true
    }
}