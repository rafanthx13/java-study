/**
 * Created by Rafael on 12/04/2017.
 */

import java.util.Scanner;
public class Question8 {

    public static void main(String[] args){
        Scanner gear_input = new Scanner(System.in);
        int b, c, a;
        System.out.print("Enter a Integer: ");
        b = gear_input.nextInt();
        System.out.print("\nEnter a Integer: ");
        c = gear_input.nextInt();
        a = b*c;
        System.out.printf("O Resultado de a*b é = %d", a);
    }
}
