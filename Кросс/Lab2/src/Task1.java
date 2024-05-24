import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть перше число: ");
        int number1 = scanner.nextInt();

        System.out.print("Введіть друге число: ");
        int number2 = scanner.nextInt();

        int sum = number1 + number2;
        int difference = number1 - number2;
        int multiplication = number1 * number2;

        int max;


        if (sum >= difference) {
            max = sum;
        } else {
            max = difference;
        }

        if (multiplication > max) {
            max = multiplication;
        }

        System.out.println("Найбільше значення серед суми, різниці і добутку: " + max);

        scanner.close();
    }
}
