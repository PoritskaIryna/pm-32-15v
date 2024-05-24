import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    //Task1
    print("Введіть швидкість першого автомобіля: ")
    val v1 = scanner.nextInt()

    print("Введіть швидкість другого автомобіля: ")
    val v2 = scanner.nextInt()

    print("Введіть початкову відстань між автомобілями: ")
    val s = scanner.nextInt()

    print("Введіть час, який вони пробули у дорозі (в годинах): ")
    val t = scanner.nextInt()

    val relativeSpeed = v1 + v2

    val result = s - relativeSpeed * t

    println("Відстань між автомобілями через $t годин: $result км")

    //Task2
    print("Введіть двоцифрове число: ")
    var number = scanner.nextInt()

    val units = number % 10
    val tens = number / 10

    val reversedNumber = units * 10 + tens

    println("Число з перестановкою цифр: $reversedNumber")

}
