package test.shanemao.puzzler;

/**
 * @author shane
 *         <p>
 *         请为Enigma 提供一个声明，它可以使该程序打印 false
 */
public class Conundrum {
    public static void main(String[] args) {
        Enigma e = new Enigma();
        System.out.println(e.equals(e));
    }
}