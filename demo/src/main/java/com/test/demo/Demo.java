package com.test.demo;

public class Demo {
    public static void main(String[] args) {
        Person p;

        p = (Person) null;

        Demo d = new Demo();
        System.out.println(d.getPersonName(p));

    }

    private String getPersonName(Person p) {
        if (p == null) {
            return null;
        }
        return p.name;
    }
}
