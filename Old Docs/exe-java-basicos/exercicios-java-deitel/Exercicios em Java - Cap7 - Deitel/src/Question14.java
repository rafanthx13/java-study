/**
 * Created by Rafael on 04/05/2017.
 */

/*  Question 14 - Chapter 7
(Variable-Length Argument List) Write an application that calculates the product of a series
of integers that are passed to method product using a variable-length argument list. Test your method
with several calls, each with a different number of arguments.
 */

import java.util.Scanner;

public class Question14 {

    public static int product(int ... numbers){
        int i = 1, result;
        for(int j : numbers){
            System.out.printf(" %d", j);
            i *= j;
        }
        System.out.println();
        return i;
    }

    public static void main(String[] args) {
        int i = 1, result = 1;
        int array_int[] = new int[50];

        Scanner input = new Scanner(System.in);
        System.out.print("Digite numeros para Multiplicar todos eles até 50\n");
        for(int j = 0; j < 50; j++){
            System.out.printf("Digite o INT n%d ou -1\n\t==> ", j+1);
            array_int[j] = input.nextInt();
            if(array_int[j] == -1){
                i = j + 1;
                break;
            }
        }
        System.out.println();

        if(i > 3){
            System.out.println("Prod de 3 numeros:");
            result = product(array_int[0],array_int[1],array_int[2]);
            System.out.printf("\t==> %d\n", result);
        }
        if(i > 5){
            System.out.println("Prod de 5 numeros:");
            result = product(array_int[0], array_int[1], array_int[2], array_int[3], array_int[4]);
            System.out.printf("\t==> %d\n", result);
        }
        if(i > 7){
            System.out.println("Prod de 7 numeros:");
            result = product(array_int[0], array_int[1], array_int[2], array_int[3], array_int[4], array_int[5], array_int[6]);
            System.out.printf("\t==> %d\n", result);
        }
        System.out.println();



    }
}
