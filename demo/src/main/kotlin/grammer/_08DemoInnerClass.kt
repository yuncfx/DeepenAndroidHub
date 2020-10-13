package grammer

class _08DemoInnerClass {

    private var pVariable = 1
    var openVariable = 2
    internal var iVariable = 3

    class InnerClass {
        fun f() {
            // not able to access pVariable

        }
    }

    inner class RealInnerClass {
        fun f() {
            println(pVariable)
            println(openVariable)
            println(iVariable)
        }
    }
}