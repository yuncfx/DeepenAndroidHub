package java8.chapter3;

import java.util.Arrays;
import java.util.List;

public class ConsumerDemo {

    @FunctionalInterface
    public interface Consumer<T> {
        public void accept(T t);
    } 
    
    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
    
    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
    }
}
