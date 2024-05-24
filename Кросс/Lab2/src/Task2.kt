import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Введіть значення a: ")
    val a = scanner.nextDouble()

    print("Введіть значення b: ")
    val b = scanner.nextDouble()

    print("Введіть значення c: ")
    var c = scanner.nextDouble()


    val discriminant = a * a - 4 * b
    if (discriminant > 0) {
        // Два корені
        val root1 = (a - Math.sqrt(discriminant)) / 2.0
        val root2 = (a + Math.sqrt(discriminant)) / 2.0

        if (root1 < -c && root2 < -c) {
            println("Розв’язок: ($root1, $root2) U (${-c}, безкінечність)")
        } else if (root1 < -c && root2 > -c) {
            println("Розв’язок: ($root1, ${-c}) U ($root2, безкінечність)")
        } else if (root1 > -c && root2 > -c) {
            println("Розв’язок: (${-c}, $root1) U ($root2, безкінечність)")
        }
    } else if (discriminant == 0.0) {

        val root = a / 2.0

        if (root < -c) {
            println("Розв’язок: (${-c}, безкінечність)")
        } else if (root>-c){
            println("Розв’язок: ((${-c}, $root) U ($root, безкінечність)")
        }else{
            println("Розв’язок: ($root, безкінечність)")
        }
    } else {
        // Дискримінант від’ємний, немає коренів
        println("Немає розв’язку")
    }

    scanner.close()
}
