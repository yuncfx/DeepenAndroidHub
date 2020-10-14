package test.demo

import java.io.File
import java.io.FileInputStream

fun readFile(path: String): Unit {
    val stream: FileInputStream = FileInputStream(File("D:\\src\\Android\\packages\\apps\\DevCamera\\src\\com\\android\\devcamera\\Api2Camera.java"))
    val byteArray: ByteArray = ByteArray(stream.available())

    stream.read(byteArray, 0, stream.available())
    println(String(byteArray))

}

fun main(args:Array<String>) {
    readFile("")
}