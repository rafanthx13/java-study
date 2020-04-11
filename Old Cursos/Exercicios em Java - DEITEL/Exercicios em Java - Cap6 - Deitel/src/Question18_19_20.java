/**
 * Created by Rafael on 28/04/2017.
 */

// Question 18 e 19 - Chapter 6

/*
6.18 (Displaying a Square of Asterisks) Write a method squareOfAsterisks that displays a solid
square (the same number of rows and columns) of asterisks whose side is specified in integer parameter
side. For example, if side is 4, the method should display
            ****
            ****
            ****
            ****
Incorporate this method into an application that reads an integer value for side from the user and
outputs the asterisks with the squareOfAsterisks method.

 */

/*
6.19 (Displaying a Square of Any Character) Modify the method created in Exercise 6.18 to receive
a second parameter of type char called fillCharacter. Form the square using the char provided
as an argument. Thus, if side is 5 and fillCharacter is #, the method should display
    #####
    #####
    #####
    #####
    #####
Use the following statement (in which input is a Scanner object) to read a character from the user
at the keyboard:
        char fill = input.next().charAt(0);
 */

/*
6.20 (Circle Area) Write an application that prompts the user for the radius of a circle and uses
a method called circleArea to calculate the area of the circle.
 */

import java.util.Scanner;
public class Question18_19_20 {

    static public void squareOfLetter(int x, char c){
        for(int i = 0; i<x; i++){
            System.out.print("\t");
            for(int j = 0; j < x; j++){
                System.out.print(c);
            }
            System.out.println();
        }

    }

    static public void squareOfAsterisks(int x){
        for(int i = 0; i<x; i++){
            System.out.print("\t");
            for(int j = 0; j < x; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static public double cicleArea(double x){
        return (Math.PI)* Math.pow(x, 2.00);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o tamanhao do Quadrado\n\t==> ");
        int size = input.nextInt();
        System.out.println();
        Question18_19_20.squareOfAsterisks(size);
        System.out.println("\nEND - Q18");

        System.out.print("Digite o numero do tamanho do Quadrado\n\t==> ");
        size = input.nextInt();
        System.out.print("Digite a letra pra desenhar o Quadrado\n\t==> ");
        char fill = input.next().charAt(0);
        System.out.println();
        Question18_19_20.squareOfLetter(size,fill);
        System.out.println("\nEND - Q19\n\n");

        System.out.print("Digite o raio do Circulo pra calcular sua Area\n\t==> ");
        double raio = input.nextDouble();
        System.out.println("Area de Circulo de raio " + raio + " = " + Question18_19_20.cicleArea(raio));
        System.out.println("\nEND - Q20");


    }
}
