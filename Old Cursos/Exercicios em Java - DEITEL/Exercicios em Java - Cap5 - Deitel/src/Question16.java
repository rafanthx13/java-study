/**
 * Created by Rafael on 26/04/2017.
 */

/*  Question 16 - Chapter 5
5.16 (Bar Chart Printing Program) One interesting application of computers is to display
graphs and bar charts. Write an application that reads five numbers between 1 and 30. For each
number that’s read, your program should display the same number of adjacent asterisks. For example,
if your program reads the number 7, it should display *******. Display the bars of asterisks after
you read all five numbers.
 */

import java.util.Scanner;
public class Question16 {

    public static void main(String[] args) {

        int x1, x2, x3, x4, x5;
        Scanner input = new Scanner(System.in);

        do{
            System.out.print("Digite Numero de 1 ah 30 ==> ");
            x1 = input.nextInt();
        }while(x1 < 1 || x1 > 30);

        do{
            System.out.print("\nDigite Numero de 1 ah 30 ==> ");
            x2 = input.nextInt();
        }while(x2 < 1 || x2 > 30);

        do{
            System.out.print("\nDigite Numero de 1 ah 30 ==> ");
            x3 = input.nextInt();
        }while(x3 < 1 || x3 > 30);

        do{
            System.out.print("\nDigite Numero de 1 ah 30 ==> ");
            x4 = input.nextInt();
        }while(x4 < 1 || x4 > 30);

        do{
            System.out.print("\nDigite Numero de 1 ah 30 ==> ");
            x5 = input.nextInt();
        }while (x5 < 1 || x5 > 30);

        System.out.printf("\n\n%2d = ", x1);
        for(int i = 0; i < x1; i++){
            System.out.print("*");
        }

        System.out.printf("\n%2d = ", x2);
        for(int i = 0; i < x2; i++){
            System.out.print("*");
        }

        System.out.printf("\n%2d = ", x3);
        for(int i = 0; i < x3; i++){
            System.out.print("*");
        }

        System.out.printf("\n%2d = ", x4);
        for(int i = 0; i < x4; i++){
            System.out.print("*");
        }

        System.out.printf("\n%2d = ", x5);
        for(int i = 0; i < x5; i++){
            System.out.print("*");
        }





    }
}
