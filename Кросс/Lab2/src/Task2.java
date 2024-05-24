import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть значення a: ");
        double a = scanner.nextDouble();

        System.out.print("Введіть значення b: ");
        double b = scanner.nextDouble();

        System.out.print("Введіть значення c: ");
        double c = scanner.nextDouble();

        double discriminant = a * a - 4 * b;

        if (discriminant > 0) {
            // Два корені
            double root1 = (a - Math.sqrt(discriminant)) / 2.0;
            double root2 = (a + Math.sqrt(discriminant)) / 2.0;

            if (root1 < -c && root2 < -c) {
                System.out.println("Розв’язок: (" + root1 + ", " + root2 + ") U (" + (-c) + ", безкінечність)");
            } else if (root1 < -c && root2 > -c) {
                System.out.println("Розв’язок: (" + root1 + ", " + (-c) + ") U (" + root2 + ", безкінечність)");
            } else if (root1 > -c && root2 > -c) {
                System.out.println("Розв’язок: (" + (-c) + ", " + root1 + ") U (" + root2 + ", безкінечність)");
            }
        } else if (discriminant == 0.0) {
            double root = a / 2.0;

            if (root < -c) {
                System.out.println("Розв’язок: (" + (-c) + ", безкінечність)");
            } else {
                System.out.println("Розв’язок: (" + root + ", безкінечність)");
            }
        } else {
            // Дискримінант від’ємний, немає коренів
            System.out.println("Розв’язок: (" + (-c) + ", безкінечність)");
        }

    }
}
