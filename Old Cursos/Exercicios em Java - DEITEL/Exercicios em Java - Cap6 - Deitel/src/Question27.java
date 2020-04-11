/**
 * Created by Rafael on 30/04/2017.
 */

/*
6.27 (Greatest Common Divisor) The greatest common divisor (GCD) of two integers is the largest
integer that evenly divides each of the two numbers. Write a method gcd that returns the greatest
common divisor of two integers. [Hint: You might want to use Euclid’s algorithm. You can find
information about it at en.wikipedia.org/wiki/Euclidean_algorithm.] Incorporate the method
into an application that reads two values from the user and displays the result.
 */

import java.util.Scanner;


public class Question27 {

    public static int mdc(int a, int b) {
        int q, r;
        while (b != 0){
            q = a / b;
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Maximo deivisor comum");
        System.out.println("Digite o 1 numero: ");
        int a = input.nextInt();
        System.out.println("Digite o 2 numero");
        int b = input.nextInt();
        System.out.printf("O MDC DE %d e %d eh %d\n\n", a,b,Question27.mdc(a,b));
    }

}
