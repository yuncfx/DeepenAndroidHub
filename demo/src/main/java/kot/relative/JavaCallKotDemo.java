package kot.relative;

import com.shengsiyuan.java2kot.HelloWorld;
import com.shengsiyuan.java2kot.MyTopClass;
import com.shengsiyuan.java2kot.People;
import com.shengsiyuan.java2kot.Person;
import com.shengsiyuan.java2kot.TopMemberKt;
import com.shengsiyuan.java2kot2.GenericEraseDemoKt;
import com.shengsiyuan.java2kot2.OverloadDemo;

import java.util.ArrayList;

public class JavaCallKotDemo {
    public static void main(String[] args) {
        // 我们无法通过new关键字来创建Kotlin编译器自动生成的以Kt结尾的类的实例
        // 因为在生成的字节码中没有不带参数的构造方法
        // compile error
        // TopMemberKt topMemberKt = new TopMemberKt();

        MyTopClass topClass = new MyTopClass();
        TopMemberKt.setStr("welcome");
        System.out.println(TopMemberKt.getStr());

        TopMemberKt.test();

        HelloWorld.myPrint();
        HelloWorld.myPrint2();

        Person person = new Person();
        System.out.println(person.getName());
        System.out.println(person.age);

        System.out.println(People.age);
        System.out.println(People.Companion.getName());

        People.Companion.test1();
        People.Companion.test2();

        People.test2();

        System.out.println(GenericEraseDemoKt.myFilter(new ArrayList<>()));
        System.out.println(GenericEraseDemoKt.myFilter2(new ArrayList<>()));

        OverloadDemo overloadDemo = new OverloadDemo(1);
        OverloadDemo overloadDemo2 = new OverloadDemo(1, "welcome");

    }
}
