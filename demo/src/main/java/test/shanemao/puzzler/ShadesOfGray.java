package test.shanemao.puzzler;

/**
 * @author shane
 *         <p>
 *         可以证明，在这样的上下文环境中，有一条规则决定着程序的行为，即当一个变
 *         量和一个类型具有相同的名字，并且它们位于相同的作用域时，变量名具有优先
 *         权[JLS 6.5.2]。变量名将遮掩（obscure）类型名[JLS 6.3.2]。
 *         <p>
 *         <p>
 *         类应该以一个大写字母开头，以 MixedCase 的形式书写；变量应该以一个小写字母
 *         开头， 以 mixedCase 的形式书写； 而常量应该以一个大写字母开头， 以 ALL_CAPS
 *         的方式书写。单个的大写字母只能用于类型参数，就像在泛型接口 Map<K,V>中
 *         那样。包名应该以 lower.case 的方式命名[JLS 6.8]
 *         <p>
 *         为了避免常量名与类名的冲突， 在类名中应该将首字母缩拼词当作普通的词处理
 *         [EJ Item 38]。例如，一个表示全局唯一标识符的类应该被命名为 Uuid，而不
 *         是 UUID，尽管其首字母缩拼词通常被写为 UUID。（Java 平台库就违反了这项建
 *         议， 因为它具有 UUID、 URL 和 URI 这样的类名。 ） 为了避免变量名与包名的冲突，
 *         请不要使用顶层的包名或领域名作为变量的名字， 特别是不要将一个变量命名为
 *         com、org、net、edu、java 或 javax。
 */
public class ShadesOfGray {
    public static void main(String[] args) {

        // White
        System.out.println(X.Y.Z);
    }
}

class X {
    static class Y {
        static String Z = "Black";
    }

    static C Y = new C();
}

class C {
    String Z = "White";
}

class Test extends X {
    public void a() {
        //用一个表达式而不是类型名来访问一个静态成员是合法的，但却是一种有问题的用法。
        System.out.println(((X.Y) null).Z);
    }

    static class Xy extends X.Y {
    }

    public void b() {
        System.out.println(Xy.Z);
    }
}