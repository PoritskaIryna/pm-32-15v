import kotlin.math.cos
import kotlin.math.sin

fun main(args: Array<String>) {
    println("Введіть межі для x")
    val a: Double = readln().toDouble()
    val b: Double = readln().toDouble()
    var x = a
    val hx = (b - a) / 7
    println("Введіть межі для y")
    val c: Double = readln().toDouble()
    val d: Double = readln().toDouble()
    var y = c
    val hy = (d - c) / 7
    var u: Double
    print("y\\x ")
    for (i in 0..7) {
        print("%8.2f".format(x + hx * i))
    }
    println()
    for (i in 0..7) {
        print("%5.2f".format(y))
        x = a
        for (j in 0..7) {
            u = sin(x * y) + cos(x / y)
            print("%8.2f".format(u))
            x += hx
        }
        println()
        y += hy
    }
}