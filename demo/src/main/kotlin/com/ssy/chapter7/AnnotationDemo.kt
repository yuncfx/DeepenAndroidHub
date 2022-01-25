package com.ssy.chapter7

import junit.framework.Assert.assertEquals
import kot.relative.Ann
import kot.relative.AnnWithArrayValue
import kot.relative.AnnWithValue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/*
    P34 注解(annotation)
    Java中注解 @interface

    @Target：指定可以用注解注解的元素的可能种类（例如类、函数、属性和表达式）；
    @Retention 指定注解是否存储在编译后的类文件中，以及是否在运行时通过反射可见（默认情况下，两者都为真）；
        SOURCE,//源代码时期(SOURCE): 注解不会存储在输出class字节码中
        BINARY,//编译时期(BINARY): 注解会存储出class字节码中，但是对反射不可见
        RUNTIME//运行时期(RUNTIME): 注解会存储出class字节码中，也会对反射可见, 默认是RUNTIME
    @Repeatable 允许在单个元素上多次使用相同的注解；某些情况下，注解里面的方法可以拆开来使用，因此需要注解多次，如Java中的：
        @Schedule(dayOfMonth="last")
        @Schedule(dayOfWeek="Fri", hour="23")
        public void doPeriodicCleanup() { ... }
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

annotation class Suspendable

// 注解作用域lambda上，其实是作用于方法的invoke()函数上
val f = @Suspendable {

}

class Tests {
    // apply @Rule annotation to property getter
    @get:Rule
    val tempFolder = TemporaryFolder()

    @Test
    fun simple() {
        val f = tempFolder.newFile()
        assertEquals(42, getTheAnswer())
    }

    private fun getTheAnswer(): Int {
        return 1
    }
}

@Ann(intValue = 1, stringValue = "abc")
class C

@AnnWithValue("abc")
class C1

@AnnWithArrayValue("abc", "foo", "bar", names = ["abc", "foo", "bar"])
class C2

// Values of an annotation instance are exposed as properties to Kotlin code
fun foo(ann: AnnWithValue) {
    val i = ann.value
}