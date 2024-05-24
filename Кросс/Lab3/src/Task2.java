import java.util.Scanner;
public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a, b, c, d, u;
        System.out.println("Введіть межі для x");
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        double x = a, hx = (b - a)/7 ;
        System.out.println("Введіть межі для y");
        c = scanner.nextDouble();
        d = scanner.nextDouble();
        double y = c, hy = (d - c)/7 ;
        System.out.print("y\\x ");
        for (int i = 0; i < 8; i++)
            System.out.printf("%8.2f",x + hx*i);
        System.out.println();
        for (int i = 0; i < 8; i++){
            System.out.printf("%5.2f",y);
            x = a;
            for (int j = 0; j < 8; j++) {
                u = Math.sin(x*y) + Math.cos(x/y);
                System.out.printf("%8.2f", u);
                x += hx;
            }
            System.out.println();
            y += hy;
        }
    }
}