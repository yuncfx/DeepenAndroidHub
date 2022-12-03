import bean.Person

class TvMi1SController {
  fun turnOn() {}

  fun turnOff() {}
}

class TvMi2SController {
  fun turnOn() {}

  fun turnOff() {}
}

open class TV {}

class XiaoMi1 : TV() {}

class XiaoMi2 : TV() {}

class Controller<T : TV> {
  fun turnOn(tv: T) {}

  fun turnOff(tv: T) {}
}

open class Animal {}
class Cat : Animal() {}
class Dog : Animal() {}

fun foo(list:MutableList<Animal>) {
  list.add(Dog())
  val animal = list[0]
}

fun main(args: Array<String>) {
  val controller = Controller<XiaoMi1>()
  controller.turnOn(XiaoMi1())

  val controller2 = Controller<XiaoMi2>()
  controller2.turnOff(XiaoMi2())
  val t:MutableList<Cat> = mutableListOf<Cat>(Cat())
  // foo(t)

  val restaurant = findRestaurant()
  // 注意这里
  val food: Any? = restaurant.orderFood() // 返回值可能是：任意类型

}



class Restaurant<T> {
  fun orderFood(): T? {
    return null
  }
}
fun findRestaurant(): Restaurant<*> {
  return Restaurant<Person>()
}