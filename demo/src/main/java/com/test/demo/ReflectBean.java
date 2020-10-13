package com.test.demo;

import java.io.Serializable;

public class ReflectBean /*extends FatherBean*/ implements Serializable {

        private static final long serialVersionUID = -879739473934L;

        public ReflectBean() {

        }

//        public ReflectBean(String name) {
//            this.name = name;
//        }
//
//        public ReflectBean(String name, String address) {
//            this.name = name;
//            this.address = address;
//        }
//
//        public ReflectBean(String name, String address, int age) {
//            this.name = name;
//            this.address = address;
//            this.age = age;
//        }

        String name;
        String address;
        int age;

        @Override
        public String toString() {
            return "ReflectBean{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", age=" + age +
                    '}';
        }

        public static void print() {
            System.out.println("print invoked!");
        }

        public void reflect1() {
            System.out.println("Java 反射机制 - 调用某个类的方法1.");
        }

        public void reflect2(int age, String name) {
            System.out.println("Java 反射机制 - 调用某个类的方法2.");
            System.out.println("age -> " + age + ". name -> " + name);
        }
    }