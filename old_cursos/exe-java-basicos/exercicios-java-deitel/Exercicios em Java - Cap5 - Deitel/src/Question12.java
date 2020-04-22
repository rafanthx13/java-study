/**
 * Created by Rafael on 24/04/2017.
 */

/*  Question 12 - Chapter 5
(Calculating the Product of Odd Integers) Write an application that calculates the product
of the odd integers from 1 to 15.
 */

public class Question12 {

    public static void main(String[] args) {
        int produto = 1;
        for(int i = 1; i < 16; i += 2){
            System.out.printf("produto = %d * %d\n",produto,i);
            produto *= i;

        }
        System.out.printf("\nProduto dos Impares de 1 ah 15 eh: %d\n\n", produto);
    }
}
