package test.shanemao.puzzler;

import test.shane.puzzler.another.CodeTalk;

/**
 * @author shane
 */
public class TypeIt {
    private static class ClickIt extends CodeTalk {
        void printMessage() {
            System.out.println("Hack");
        }
    }

    /**
     * 一个包内私有的方法不能被位
     * 于另一个包中的某个方法直接覆写[JLS 8.4.8]。在程序中的这两个 twoMessage
     * 方法是无关的，它们仅仅是具有相同的名字而已。当程序在 hack 包内调用
     * printMessage 方法时，运行的是 hack.TypeIt.ClickIt.printMessage 方法。这
     * 个方法将打印 Click，这也就解释了我们所观察到的行为。
     */
    public static void main(String[] args) {
        ClickIt clickit = new ClickIt();
        clickit.doIt();
    }
}