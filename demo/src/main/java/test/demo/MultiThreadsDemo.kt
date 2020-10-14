package test.demo

import org.junit.Test

val array: IntArray = IntArray(1000) { i -> i + 1000 }

@Volatile
var count: Int = 0

@Volatile
var sum: Int = 0

class MultiThreadsDemo {
    @Test
    fun test() {
        val thread1 = Thread(MyRunnable())
        val thread2 = Thread(MyRunnable())
        val thread3 = Thread(MyRunnable())
        val thread4 = Thread(MyRunnable())
        val thread5 = Thread(MyRunnable())
        var start = System.nanoTime()
        thread1.start()
        thread1.start()
        thread2.start()
        thread3.start()
        thread4.start()
        thread5.start()

        thread1.join()
        thread2.join()
        thread3.join()
        thread4.join()
        thread5.join()
        var duration = System.nanoTime() - start
        println("threadSum:$sum")
        println("duration:$duration")

        start = System.nanoTime()
        var singleSum = 0
        for (i in array) {
            singleSum += i
        }
        duration = System.nanoTime() - start

        println("singleSum:$singleSum")
        println("duration2:$duration")
    }
}

class MyRunnable : Runnable {

    override fun run() {
        while (count < array.size) {
            sum += array[count]
            synchronized(array) {
                count++
            }
        }
    }
}
