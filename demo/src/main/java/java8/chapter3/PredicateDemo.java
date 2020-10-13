package java8.chapter3;

import java.util.ArrayList;
import java.util.List;

public class PredicateDemo {

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }
    
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        
        for (T t : list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        
        return results;
    }
    
    
    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    private List<String> listOfStrings = new ArrayList<>();
    List<String> nonEmpty = filter(listOfStrings , nonEmptyStringPredicate);
}
