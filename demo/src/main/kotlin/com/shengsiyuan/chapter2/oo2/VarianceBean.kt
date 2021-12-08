package com.shengsiyuan.chapter2.oo2

open class VariancePerson(val name: String, val age: Int) {
    open fun toWork() {
        println("我是工人$name，我要好好干活！！！")
    }
}

class VarianceWorker1(name: String, age: Int) : VariancePerson(name, age) {
    override fun toWork() {
        println("我是1工人$name，我要好好干活！！！")
    }
}

class VarianceWorker2(name: String, age: Int) : VariancePerson(name, age) {
    override fun toWork() {
        println("我是2工人$name，我也要好好干活！！！")
    }
}
