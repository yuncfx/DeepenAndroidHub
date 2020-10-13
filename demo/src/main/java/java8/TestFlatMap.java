package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shane
 */

public class TestFlatMap {
    public static void main(String[] args) {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> stream = Arrays.stream(arrayOfWords);

        List<String> uniqueCharacters = stream.map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(uniqueCharacters);
    }
}
