import java.math.BigInteger
import java.util.*
import kotlin.math.pow

fun main() {
    val scanner = Scanner(System.`in`)
    println("Введіть значення x:")
    val x = scanner.nextDouble()
    scanner.close()

    var s = 0.0
    val eps = 0.00001
    var k = 0
    var u = Math.pow(x, k.toDouble()) / factorial(k).toDouble()

    while (Math.abs(u) > eps) {
        val factorialResult = factorial(k)
        s += u
        k++
        u = Math.pow(x, k.toDouble()) / factorial(k).toDouble()
    }

    println("Значення суми s = %.6f".format(s))
    println("Значення функції: %.6f".format(Math.E.pow(x)))
    println("Кількість доданків $k")
}

fun factorial(n: Int): BigInteger {
    require(n >= 0) { "Factorial is not defined for negative numbers" }

    var result = BigInteger.ONE
    for (i in 1..n) {
        result = result.multiply(BigInteger.valueOf(i.toLong()))
    }

    return result
}
