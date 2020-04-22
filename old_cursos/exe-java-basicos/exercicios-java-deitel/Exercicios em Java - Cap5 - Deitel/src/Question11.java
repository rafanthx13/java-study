/**
 * Created by Rafael on 24/04/2017.
 */

/*  Question 11  - Chapter 5
(Find the Smallest Value) Write an application that finds the smallest of several integers.
Assume that the first value read specifies the number of values to input from the user.
 */

import java.util.Scanner;
public class Question11 {

    public static void main(String[] args) {
        int menor = -1, choice;
        boolean first = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Escreva valores ate digitar -1\n");
        do{
            System.out.print("Digite: ");
            choice = input.nextInt();
            if(first == true){
                menor = choice;
                first = false;
            }
            if(choice < menor){
                if(choice != -1){
                    menor = choice;
                }
            }
            System.out.println();
        }while(choice != -1);
        System.out.printf("O menor valor digitado: %d", menor);

    }
}
