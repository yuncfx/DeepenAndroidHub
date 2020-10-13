package com.design.pattern.visitor_pattern2;

/**
 *
 * 既然有 Double Dispatch，对应的就有 Single Dispatch。所谓 Single Dispatch，指的
 * 是执行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参
 * 数的编译时类型来决定。所谓 Double Dispatch，指的是执行哪个对象的方法，根据对象
 * 的运行时类型来决定；执行对象的哪个方法，根据方法参数的运行时类型来决定。
 *
 * 如何理解“Dispatch”这个单词呢？ 在面向对象编程语言中，我们可以把方法调用理解为
 * 一种消息传递，也就是“Dispatch”。一个对象调用另一个对象的方法，就相当于给它发
 * 送一条消息。这条消息起码要包含对象名、方法名、方法参数。
 * 如何理解“Single”“Double”这两个单词呢？“Single”“Double”指的是执行哪个
 * 对象的哪个方法，跟几个因素的运行时类型有关。我们进一步解释一下。Single Dispatch
 * 之所以称为“Single”，是因为执行哪个对象的哪个方法，只跟“对象”的运行时类型有
 * 关。Double Dispatch 之所以称为“Double”，是因为执行哪个对象的哪个方法，跟“对
 * 象”和“方法参数”两者的运行时类型有关。
 * 具体到编程语言的语法机制，Single Dispatch 和 Double Dispatch 跟多态和函数重载直
 * 接相关。当前主流的面向对象编程语言（比如，Java、C++、C#）都只支持 Single
 * Dispatch，不支持 Double Dispatch。
 *
 * 在面向对象编程语言中，方法调用可
 * 以理解为一种消息传递（Dispatch）。一个对象调用另一个对象的方法，就相当于给它发
 * 送一条消息，这条消息起码要包含对象名、方法名和方法参数
 *
 * 所谓 Single Dispatch，指的是执行哪个对象的方法，根据对象的运行时类型来决定；执行
 * 对象的哪个方法，根据方法参数的编译时类型来决定。所谓 Double Dispatch，指的是执
 * 行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参数的
 * 运行时类型来决定。
 *
 * 具体到编程语言的语法机制，Single Dispatch 和 Double Dispatch 跟多态和函数重载直
 * 接相关。当前主流的面向对象编程语言（比如，Java、C++、C#）都只支持 Single
 * Dispatch，不支持 Double Dispatch。
 */
public class DemoMain {
    public static void main(String[] args) {
        SingleDispatchClass demo = new SingleDispatchClass();
        ParentClass p = new ChildClass();
        //执行哪个对象的方法，由对象的实际类型决定
        demo.polymorphismFunction(p);
        //执行对象的哪个方法，由参数对象的声明类型决定
        demo.overloadFunction(p);

        demo.overloadFunction2(p);
    }
}