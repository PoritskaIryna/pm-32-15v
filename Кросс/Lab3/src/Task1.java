import java.util.Scanner;
import java.math.BigInteger;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть значення x:");
        double x = scanner.nextDouble();
        scanner.close();

        double s = 0;
        double eps = 0.00001;
        int k = 0;
        double u = Math.pow(x, k) / factorial(k).doubleValue();
        while (Math.abs(u) > eps) {
            BigInteger factorialResult = factorial(k);
            s += u;
            k++;
            u = Math.pow(x, k) / factorial(k).doubleValue();
        }

        System.out.printf("Значення суми s = %.6f\n", s);
        System.out.printf("Значення функції: %.6f\n", Math.pow(Math.E, x));
        System.out.println("Кількість доданків " + (k));
    }

    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}
