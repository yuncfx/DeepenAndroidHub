package java8.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import java8.chapter1.Apple;
import java8.interfaces.AppleFormatter;
import java8.interfaces.Predicate;


public class ChapterTwoDemo {
    private static List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
            new Apple(155, "green"), new Apple(120, "red"));
    private static List<Integer> numbers = Arrays.asList(new Integer("2"),
            new Integer("4"), new Integer("7"), new Integer("9"));

    public static void main(String[] args) {
        prettyPrintApple(inventory, new AppleFormatter() {

            @Override
            public String accept(Apple a) {
                return a.getWeight() > 150 ? " big apple " : "small apple";
            }

        });

        List<Apple> result = filterApples(inventory,
                (Apple apple) -> "red".equals(apple.getColor()));

        List<Apple> redApples = filter(inventory,
                (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        
        inventory.sort((Apple a1, Apple a2)-> a1.getWeight() - a2.getWeight());
        
        inventory.sort((a1,a2)->a1.getWeight()-a2.getWeight());
        
        inventory.sort(Comparator.comparing(Apple::getWeight));
        
        Thread t = new Thread(()->System.out.println("Hello Thread"));
        
    }

    public static void prettyPrintApple(List<Apple> inventory,
            AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }

        return result;
    }
    
    
}
