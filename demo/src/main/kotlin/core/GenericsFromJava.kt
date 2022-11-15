package core

import kot.variance.Person
import kot.variance.Worker1
import kot.variance.Worker2

fun setWork(studentList: List<Person>) {
  for (o in studentList) {
    o?.toWork()
  }
}

fun setWorker2(studentList: List<Worker1>) {
  for (o in studentList) {
    println("哈哈 $o")
  }
}

fun main() {
  val personArrayList: MutableList<Person> = ArrayList()
  personArrayList.add(Person("aaa", 11))
  personArrayList.add(Worker1("bbb", 12))
  personArrayList.add(Worker2("ccc", 13))

  val worker1List: MutableList<Worker1> = ArrayList()
  worker1List.add(Worker1("ddd", 14))
  val worker2List: MutableList<Worker2> = ArrayList()
  worker2List.add(Worker2("eee", 15))
  setWork(personArrayList)

  // List<Worker1>和List<Worker2>不是List<Person>的子类
  setWork(worker1List)
  setWork(worker2List)

  // not compile, List<Person>显然不能传给List<Worker1>
  // setWorker2(personArrayList);
  setWorker2(worker1List)
}
