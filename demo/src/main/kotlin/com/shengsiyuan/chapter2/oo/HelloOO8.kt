package com.shengsiyuan.chapter2.oo

open class BaseClass {
    open fun method() {

    }
}

abstract class ChildClass: BaseClass() {
    // 抽象类继承普通类，可以使用抽象方法重写父类中的普通方法
    abstract override fun method()
}