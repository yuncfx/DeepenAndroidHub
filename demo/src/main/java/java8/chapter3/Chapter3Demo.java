package java8.chapter3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

import java8.chapter1.Apple;
import java8.interfaces.BufferedReaderProcessor;


public class Chapter3Demo {

    Comparator<Apple> byWeight = new Comparator<Apple>() {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight() - (a2.getWeight());
        }
    };
    
    Comparator<Apple> byWeight2 =
        (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();
            

    public static void main(String[] args) {

    }
    
    //注意你使用了Java 7中的带资源的 try 语句，它已经简化了代码，因为你不需要显式地关闭资源了
    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }
    
    public static String processFile(BufferedReaderProcessor p) {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    
}
