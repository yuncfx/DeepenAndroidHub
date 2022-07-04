package test.demo

class CloneDemo {

}

fun main() {
  val list = ArrayList<Person>().apply {
    add(Person("zhangsan", 12))
    add(Person("lisi", 24))
  }
  val list2 = ArrayList(list)
  val list3 = list.clone()
  val list4 = ArrayList<Person>()
  list4.addAll(list)
  val list5 = ArrayList<Person>()

  //
  for (i in list) {
    list5.add(i.clone() as Person)
  }


  println(list.hashCode())
  println(list2.hashCode())
  println(list3.hashCode())
  println(list4.hashCode())
  println(list5.hashCode()) // deep clone

  val map = HashMap<String, String>().apply {

  }
}