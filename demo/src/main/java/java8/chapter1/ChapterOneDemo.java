package java8.chapter1;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChapterOneDemo {
    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);
    }

    // none java 8 style begin
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }

        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }
    // none java 8 style end
    
    // java 8 style begin
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
    
    public interface Predicate<T> {
        boolean test(T t);
    }
    
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        
        for(Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        
        return result;
    }

    private List<Apple> inventory =Arrays.asList(new Apple(80,"green"),
            new Apple(155, "green"),
            new Apple(120, "red"));
    
    @Test
    public void test1() {
        filterApples(inventory, Apple::isGreenApple);
        filterApples(inventory, Apple::isHeavyApple);
    }
    // java 8 style end

}
