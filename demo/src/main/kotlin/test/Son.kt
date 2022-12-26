package test

class Son(): Father() {
  var soundEffectNode :Int = -1
    get() = field
    set(value) {
      field = value
      println("setting value:$value")
    }
  init {

  }

  override fun initView() {
    soundEffectNode = 100
  }

//  private var soundEffectNode :Int by Delegates.observable(-11){_,old,new->
//    println(("soundEffectNodeValue = old = $old, new:$new"))
//    val elements = Thread.currentThread().stackTrace
//    for (i in 1 until elements.size) {
//      val s = elements[i]
//      println(
//        "\tat " + s.className + "." + s.methodName + "(" + s.fileName + ":" + s.lineNumber + ")"
//      )
//    }
//  }
}

fun main() {
  val son = Son()
  println(son.soundEffectNode)
}