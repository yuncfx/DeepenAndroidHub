package java8.chapter2;


import java8.chapter1.Apple;
import java8.interfaces.AppleFormatter;

public class AppleSimpleFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}