package com.ssy.chapter2.oo2

/*
    p16 精彩 讲透协变和逆变的概念。
    协变与逆变相关

    Kotlin: 声明处协变；Java: 使用处协变

    Kotlin中的out关键字叫做 variance annotation, 因为它是在类型参数声明处所指定的，
    因此我们称之为声明处变量 (declaration-site variance)

    对于Java来说则是使用处变量 (use-site variance)，其中类型通配符使得类型协变成为可能。
 */

/**
 * 如果泛型类只是将泛型类型作为其方法的输出类型，那么我们就可以使用out
 *
 * produce = output = out
 */
interface Producer<out T> {
    fun produce(): T
}

/**
 * 如果泛型类只是将泛型类型作为其方法的输入类型，那么我们就可以使用in
 * consume = input = in
 */
interface Consumer<in T> {
    fun consume(item: T)
}

/**
 * 如果泛型类同时将泛型类型作为其方法的输入类型与输出类型，那么我们就不能使用out与in来修饰泛型
 */
interface ProducerConsumer<T> {
    fun produce(): T
    fun consume(item: T)
}

open class Fruit

open class Apple : Fruit()

class ApplePear : Apple()

class FruitProducer : Producer<Fruit> {
    override fun produce(): Fruit {
        println("Produce Fruit")
        return Fruit()
    }
}

class AppleProducer : Producer<Apple> {
    override fun produce(): Apple {
        println("Produce Apple")
        return Apple()
    }

    // 这里Apple可以声明为参数
    fun consumer(apple: Apple) {}
}

class ApplePearProducer : Producer<ApplePear> {
    override fun produce(): ApplePear {
        println("Produce ApplePear")
        return ApplePear()
    }
}

fun main(args: Array<String>) {
    // 对于"out"泛型来说，我们可以将子类型对象赋给父类型引用
    val producer1: Producer<Fruit> = FruitProducer()
    val producer2: Producer<Fruit> = AppleProducer()
    val producer3: Producer<Fruit> = ApplePearProducer()

    producer2.produce()

    // 对于"in"泛型来说， 我们可以将父类型对象赋给子类型引用
    val consumer1: Consumer<ApplePear> = Human()
    val consumer2: Consumer<ApplePear> = Man()
    val consumer3: Consumer<ApplePear> = Boy()

}

class Human : Consumer<Fruit> {
    override fun consume(item: Fruit) {
        println("Consume Fruit")
    }
}

class Man : Consumer<Apple> {
    override fun consume(item: Apple) {
        println("Consume Apple")
    }
}

class Boy : Consumer<ApplePear> {
    override fun consume(item: ApplePear) {
        println("Consume ApplePear")
    }
}


