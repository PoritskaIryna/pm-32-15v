import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Task1
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть швидкість першого автомобіля: ");
        int V1 = sc.nextInt();

        System.out.print("Введіть швидкість другого автомобіля: ");
        int V2 = sc.nextInt();

        System.out.print("Введіть початкову відстань між автомобілями: ");
        int S = sc.nextInt();

        System.out.print("Введіть час,який вони пробули у дорозі: ");
        int T = sc.nextInt();

        int result=S-(V1+V2)*T;
        System.out.println("Відстань між автомобілями через " + T +" годин: " + result);

        //Task2
        System.out.print("Введіть двоцифрове число: ");
        int number = sc.nextInt();

        int units = number % 10;
        int tens = number / 10;

        int reversedNumber = units * 10 + tens;

        System.out.println("Число з перестановкою цифр: " + reversedNumber);
    }
}