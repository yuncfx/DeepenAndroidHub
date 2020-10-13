package grammer

fun loop() {
    var flag:Boolean = false
    while (flag) {
        flag = false
    }

    for (i in 1..10) {
        println(i)
    }

    do {
        flag = false
        continue
        break
    } while (flag)

    if (flag) {
        println(flag)
    } else if (!flag) {
        println(flag)
    }

    when(flag) {
        true -> println("true")
        else -> println("false")
    }


}