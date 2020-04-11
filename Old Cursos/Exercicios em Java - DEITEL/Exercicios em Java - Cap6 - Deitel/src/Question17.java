/**
 * Created by Rafael on 28/04/2017.
 */

/*
6.17 (Even or Odd) Write a method isEven that uses the remainder operator (%) to determine
whether an integer is even. The method should take an integer argument and return true if the integer
is even and false otherwise. Incorporate this method into an application that inputs a sequence
of integers (one at a time) and determines whether each is even or odd.
 */

import java.util.Random;
import java.util.Scanner;
public class Question17 {

    // OBS: Vou deixar os metodos static para nao presisa instancia um objeto na app

    public static boolean isEven(int n){
        if(n % 2 == 0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        Scanner input = new Scanner(System.in);
        int qtd, num;
        System.out.print("Digite a qtd de numero a serem ditos PARES:\n\t==> ");
        qtd = input.nextInt();
        System.out.println();
        for(int i = 0; i < qtd; i++){
            num = random.nextInt();
            System.out.println("\t" + num + "\t(" + i + ")" + " ==> " + Question17.isEven(num));
        }
        System.out.println("\nEND");
    }

}
