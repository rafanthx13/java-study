/**
 * Created by Rafael on 20/04/2017.
 */
import java.util.Scanner;

public class Question30 {

    //Determina se um numero eh palindrmoo ou nao
    public static void main(String[] args) {

        int number;
        int x1, x2, x3, x4, x5;
        Scanner gear_input = new Scanner(System.in);
        System.out.println("Digite o numero de 5 Digitos");
        number = gear_input.nextInt();
        if(number > 10000){
            x1 = number/10000;
            x2 = (number%10000)/1000;
            x3 = ((number%10000)%1000)/100;
            x4 = (((number%10000)%1000)%100)/10;
            x5 = ((((number%10000)%1000)%100)%10)/1;
            if(x1 == x5 && x2 == x4){
                System.out.println("EH PALINDROMO");
            }else{
                System.out.println("NAO EH PALINDORMO");
            }
        }else{
            System.out.println("\n ERRO: Numero Invalido");
        }





    }
}
