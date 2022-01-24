package com.ssy.chapter7

/*
    P34 注解(annotation)
    Java中注解 @interface

    @Target：指定可以用注解注解的元素的可能种类（例如类、函数、属性和表达式）；
    @Retention 指定注解是否存储在编译后的类文件中，以及是否在运行时通过反射可见（默认情况下，两者都为真）；
    @Repeatable 允许在单个元素上多次使用相同的注解；
    @MustBeDocumented specifies that the annotation is part of the public API and should be included
        in the class or method signature shown in the generated API documentation.
 */
// meta-annotation (元注解)

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.EXPRESSION,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER
)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
@Repeatable
annotation class MyAnnotation

@MyAnnotation
class MyClass {
    @MyAnnotation
    fun myMethod(@MyAnnotation a: Int): Int {
        return (@MyAnnotation 10)
    }
}

class MyClass2 @MyAnnotation constructor(a: Int) {

}

class MyClass3 {
    var a: Int? = null
        @MyAnnotation set
}