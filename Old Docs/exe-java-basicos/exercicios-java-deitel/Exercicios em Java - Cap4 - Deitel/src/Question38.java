/**
 * Created by Rafael on 20/04/2017.
 */

import java.util.Scanner;
public class Question38 {

    //Criptografia SImples
    public static void main(String[] args) {
        int x1, x2, x3, x4;
        Scanner input = new Scanner(System.in);
        System.out.println("Digite um numero de 4 algarismo pra ser encriptado:");
        int number = input.nextInt();

        x1 = number/1000;
        x2 = (number%1000)/100;
        x3 = ((number%1000)%100)/10;
        x4 = (((number%1000)%100)%10)/1;

        x1 = (x1 + 7) % 10;
        x2 = (x2 + 7) % 10;
        x3 = (x3 + 7) % 10;
        x4 = (x4 + 7) % 10;

        System.out.printf("Numero Com Criptografia: %d ==> %d%d%d%d\n\n",number,x3,x4,x1,x2);

        int y1 = x1, y2 = x2, y3 = x3, y4 = x4;

        if(y1 < 7){
            y1 += 10;
            y1 -=7;
        }else{
            y1 -= 7;
        }

        if(y2 < 7){
            y2 += 10;
            y2 -=7;
        }else{
            y2 -= 7;
        }

        if(y3 < 7){ // 1
            y3 += 10;
            y3 -=7;
        }else{
            y3 -= 7;
        }

        if(y4 < 7){
            y4 += 10;
            y4 -=7;
        }else{
            y4 -= 7;
        }

        System.out.printf("Descriptografando pra %d: %d%d%d%d <==> %d%d%d%d\n",number,x3,x4,x1,x2,y1,y2,y3,y4);
    }
}
