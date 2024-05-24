import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Введіть перше число: ")
    val number1 = scanner.nextInt()

    print("Введіть друге число: ")
    val number2 = scanner.nextInt()

    val sum = number1 + number2
    val difference = number1 - number2
    val multiplication = number1 * number2

    var max = sum

    // Порівнюємо max з різницею
    if (difference > max) {
        max = difference
    }

    // Порівнюємо max з добутком
    if (multiplication > max) {
        max = multiplication
    }

    println("Найбільше значення серед суми, різниці і добутку: $max")

    scanner.close()
}
