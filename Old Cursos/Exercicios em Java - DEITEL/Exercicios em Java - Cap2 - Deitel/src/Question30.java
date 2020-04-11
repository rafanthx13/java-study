/**
 * Created by Rafael on 12/04/2017.
 */
import java.util.Scanner;

public class Question30 {

    /*  Questao 2.30: Faça um programa que, dado um numero de 5 digitos, dividao e escreva seus algarismo
        de forma separa. (Use divisao e resto de divisao). Ex: 45678 ==> 4 5 6 7 8
     */

    public static void main(String[] args){
        int number;
        int x1, x2, x3, x4, x5;
        Scanner gear_input = new Scanner(System.in);
        System.out.println("Digite o numero de 5 Digitos");
        number = gear_input.nextInt();
        x1 = number/10000;
        x2 = (number%10000)/1000;
        x3 = ((number%10000)%1000)/100;
        x4 = (((number%10000)%1000)%100)/10;
        x5 = ((((number%10000)%1000)%100)%10)/1;
        System.out.printf(" %d = %d %d %d %d %d", number, x1, x2, x3, x4, x5);
    }
}
