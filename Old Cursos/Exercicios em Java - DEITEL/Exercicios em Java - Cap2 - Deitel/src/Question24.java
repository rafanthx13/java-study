/**
 * Created by Rafael on 12/04/2017.
 */
import java.util.Scanner;

public class Question24 {

    /*
    Questao 2.24 :Escreva um programa que leia 5 inteiros e determine e imrpima
    o maior e menor deles. Usando as tecnica que voce aprendeu.
     */

    public static void main(String[] args){

        int x1, x2, x3, x4, x5, maior, menor;
        Scanner gear_input = new Scanner(System.in);

        System.out.println("Digite o primeiro inteiro:");
        x1 = gear_input.nextInt();
        System.out.println("Digite o segundo inteiro:");
        x2 = gear_input.nextInt();
        System.out.println("Digite o terceiro inteiro:");
        x3 = gear_input.nextInt();
        System.out.println("Digite o quarto inteiro:");
        x4 = gear_input.nextInt();
        System.out.println("Digite o quinto inteiro:");
        x5 = gear_input.nextInt();

        if(x1 >= x2 && x1 >= x3 && x1 >= x4 && x1 >= x5){
            maior = x1;
        }else if(x2 >= x1 && x2 >= x3 && x2 >= x4 && x2 >= x5){
            maior = x2;
        }else if(x3 >= x1 && x3 >= x2 && x3 >= x4 && x3 >= x5){
            maior = x3;
        }else if(x4 >= x1 && x4 >= x2 && x4 >= x3 && x5 >= x4){
            maior = x4;
        }else{
            maior = x5;
        }

        System.out.printf("O maior Inteiro eh: %d", maior);
    }
}
