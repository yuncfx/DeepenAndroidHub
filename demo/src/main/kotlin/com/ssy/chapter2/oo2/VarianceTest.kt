package com.ssy.chapter2.oo2


fun main() {
    val personArrayList: MutableList<VariancePerson> = ArrayList()
    personArrayList.add(VariancePerson("aaa", 11))
    personArrayList.add(VarianceWorker1("bbb", 12))
    personArrayList.add(VarianceWorker2("ccc", 13))

    val personArrayList1: MutableList<VarianceWorker1> = ArrayList()
    personArrayList1.add(VarianceWorker1("ddd", 14))
    val personArrayList2: MutableList<VarianceWorker2> = ArrayList()
    personArrayList2.add(VarianceWorker2("eee", 15))
    setWorkCovariance(personArrayList)
    setWorkCovariance(personArrayList1)
    setWorkCovariance(personArrayList2)

    setWorkerContravariance(personArrayList)
    setWorkerContravariance(personArrayList2)
}

/**
 * out是只读的， List是只读的， 所以这里out是多余的
 */
fun setWorkCovariance(studentList: List<out VariancePerson>) {
    for (o in studentList) {
        o.toWork()
    }
}


/**
 * in 可写， 读出来的是Any?，类的默认父类是Any?
 */
fun setWorkerContravariance(studentList: MutableList<in VarianceWorker2>) {
    studentList.add(VarianceWorker2("zhangsan", 23))
    if (studentList.isNotEmpty()) {
        val t: Any? = studentList[0]
        println(t)
    }
    for (o in studentList) {
        println("哈哈 " + o.toString())
    }
}


