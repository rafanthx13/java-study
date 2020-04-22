/**
 * Created by Rafael on 28/04/2017.
 */

/*      Chapter 6 - Question 10
(Rounding Numbers) To round numbers to specific decimal places, use a statement like
    y = Math.floor(x * 10 + 0.5) / 10;
which rounds x to the tenths position (i.e., the first position to the right of the decimal point), or
    y = Math.floor(x * 100 + 0.5) / 100;
which rounds x to the hundredths position (i.e., the second position to the right of the decimal
point). Write an application that defines four methods for rounding a number x in various ways:
    a) roundToInteger(number)
    b) roundToTenths(number)
    c) roundToHundredths(number)
    d) roundToThousandths(number)
For each value read, your program should display the original value, the number rounded to the
nearest integer, the number rounded to the nearest tenth, the number rounded to the nearest hundredth
and the number rounded to the nearest thousandth.
 */

import java.util.Scanner;
public class Question10 {

    public static void main(String[] args) {
        double num;
        int choice;
        Scanner input = new Scanner(System.in);
        Question_10_Round arredondador = new Question_10_Round();
        System.out.print("Digite um numero double pra arrendondar: (Ex: 1,0)\n\t==> ");
        num = input.nextDouble();
        System.out.println("Round to Integer:\t\t" + arredondador.roundToInteger(num));
        System.out.println("Round to Tenths:\t\t" + arredondador.roundToTenths(num));
        System.out.println("Round to Hundredths:\t" + arredondador.roudToHundredths(num));
        System.out.println("Round to Thousandths:\t"+ arredondador.roundToThousandths(num));
        System.out.println("");
        System.out.print("Digita um Num. para arredondar ate 10^(-X):\n\t==> ");
        choice = input.nextInt();
        System.out.println("Rount do Personalizado\nX = " + choice + " ==> " + arredondador.roundToPersonalizado(num, choice));
    }
}
