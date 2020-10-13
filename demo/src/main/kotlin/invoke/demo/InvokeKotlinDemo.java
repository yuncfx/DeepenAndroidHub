package invoke.demo;

import base.ExtensionDemoKt;

/**
 * demo for java invoking kotlin
 * @author shane
 */
public class InvokeKotlinDemo {
    public static void main(String[] args) {
        char java = ExtensionDemoKt.getLastChar("Java");
        System.out.println(java);
        StringBuilder hello = new StringBuilder("hello");
        ExtensionDemoKt.setLastChar(hello, 'c');
        System.out.println(hello);

    }
}
