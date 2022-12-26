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

/**
 * 加了in, 声明处逆变
 */
class Controller<in T> {
  fun turnOn(tv: T) {}

  fun turnOff(tv: T) {}
}

open class Animal {}
class Cat : Animal() {}
class Dog : Animal() {}

fun foo(list: MutableList<Animal>) {
  list.add(Dog())
  val animal = list[0]
}

fun fooWithList(list: List<Animal>) {
  if (list.isNotEmpty()) {
    val animal = list[0]
  }
}

fun buy(controller: Controller<XiaoMi1>) {
  val xiaoMi1 = XiaoMi1()
  controller.turnOff(xiaoMi1)
}

open class Food
class KFC : Food()

// 声明处协变
class Restaurant<out T> {
  // 不加private 编译不通过，因为可能被外部修改， 不符合只做返回值的设定
  private var instance: T? = null
  fun order(): T? {
    return instance
  }
}

// 加了in， 使用处逆变
fun buyContravariant(controller: Controller<in XiaoMi1>) {
  val xiaoMi1 = XiaoMi1()
  controller.turnOff(xiaoMi1)
}

fun orderFood(restaurant: Restaurant<Food>) {
  val food = restaurant.order()
}

// 使用处协变
fun orderFoodCovariance(restaurant: Restaurant<out Food>) {
  val food = restaurant.order()
}

fun main(args: Array<String>) {
  val controller = Controller<XiaoMi1>()
  controller.turnOn(XiaoMi1())

  val controller2 = Controller<XiaoMi2>()
  controller2.turnOff(XiaoMi2())

  val t: MutableList<Cat> = mutableListOf<Cat>(Cat())
  // foo(t) // not compile
  fooWithList(t) // compile

  val controllerTv = Controller<TV>()

  // buy(controllerTv) // not compile without `in`
  buyContravariant(controllerTv) // compile

  val restaurant = Restaurant<Food>()
  orderFood(restaurant)

  val kfc = Restaurant<KFC>()
  // orderFood(kfc) // not compile without `out`
  orderFoodCovariance(kfc)
}

fun findRestaurant(): Restaurant<*> {
  return Restaurant<Person>()
}