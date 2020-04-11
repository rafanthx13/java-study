/**
 * Created by Rafael on 04/05/2017.
 */

/*
7.19 (Airline Reservations System) A small airline has just purchased a computer for its new automated
reservations system. You’ve been asked to develop the new system. You’re to write an application
to assign seats on each flight of the airline’s only plane (capacity: 10 seats).

Your application should display the following alternatives:
 Please type 1 for First Class and
 Please type 2 for Economy.
    If the user types 1, your application should assign a seat in the firstclass section (seats 1–5).
    If the user types 2, your application should assign a seat in the economy section (seats 6–10).
Your application should then display a boarding pass indicating the person’s
seat number and whether it’s in the first-class or economy section of the plane.
Use a one-dimensional array of primitive type boolean to represent the seating chart of the
plane. Initialize all the elements of the array to false to indicate that all the seats are empty. As
each seat is assigned, set the corresponding element of the array to true to indicate that the seat is
no longer available.

Your application should never assign a seat that has already been assigned. When the economy
section is full, your application should ask the person if it’s acceptable to be placed in the first-class
section (and vice versa). If yes, make the appropriate seat assignment. If no, display the message
"Next flight leaves in 3 hours."
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Question19 {

    public static int escolha1_2(){
        int chose;
        System.out.println("+ Digite 1 para Primeira Classe\n+ Digite 2 para Segunda Classe");
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\t==> ");
            chose = scanner.nextInt();
        }while(chose != 1 && chose != 2);
        return chose;
    }

    public static int escolhaC1_C2(){
        int chose;
        System.out.println("+ Digite 1 para YES!\n+ Digite 2 para NO!");
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\t==> ");
            chose = scanner.nextInt();
        }while(chose != 1 && chose != 2);
        return chose;
    }



    public static void main(String[] args) {

        int chose = 0, assento, oc1 = 0, oc2 = 0, i = 1;
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        boolean voo[] = new boolean[10];
        int ocup[] = new int[10];
        Arrays.fill(voo, false);

        System.out.println("\tSistema de Voo\n\n");
        do{
            System.out.printf("Passageiro Numero %d\n", i);
            chose = Question19.escolha1_2();
            if(chose == 1) {
                if (oc1 != 5) {
                    do {
                        assento = 0 + rand.nextInt(5); // 0 a 4 = 1 a 5
                    } while (voo[assento] != false); // Sai quando acha false
                    voo[assento] = true;
                    ocup[assento] = i;
                    System.out.printf("\t!!!Seu assento sera de 1 CLASS eh NUMERO: %d\n\n", assento + 1);

                    oc1++;
                }else{
                    System.out.println("\t!!!WARNING!!!");
                    System.out.println("1 Class Cheia, deseja assento na 2 Class\n");
                    chose = Question19.escolhaC1_C2();
                    if(chose == 1){
                        do{
                            assento = 5 + rand.nextInt(5); // 5 a 9 = 6 a 10
                        } while (voo[assento] != false); // Sai quando acha false
                        voo[assento] = true;
                        ocup[assento] = i;
                        System.out.printf("\tSeu assento de 2 CLASS eh %d\n\n", assento + 1);
                        oc2++;
                    }else{
                        System.out.println("Proximo Voo em 3 horas BYE\n\n");
                    }
                    chose = 0; // zero o contador para nao ir na segunda classe
                }
            }

            if(chose == 2){
                if(oc2 != 5){
                    do{
                        assento = 5 + rand.nextInt(5); // 5 a 9 = 6 a 10
                    } while (voo[assento] != false); // Sai quando acha false
                    voo[assento] = true;
                    ocup[assento] = i;
                    System.out.printf("\t!!!Seu assento sera de 2 CLASS eh NUMERO: %d\n\n", assento + 1);
                    oc2++;
                }else{
                    System.out.println("\t!!!WARNING!!!");
                    System.out.println("2 Class Cheia, deseja assento na 1 Class\n");
                    chose = Question19.escolhaC1_C2();
                    if(chose == 1){
                        do {
                            assento = 0 + rand.nextInt(5); // 0 a 4 = 1 a 5
                        } while (voo[assento] != false); // Sai quando acha false
                        voo[assento] = true;
                        ocup[assento] = i;
                        System.out.printf("Seu assento de 1 CLASS eh %d\n\n", assento + 1);
                        oc1++;
                    }else{
                        System.out.println("Proximo Voo em 3 horas BYE\n\n");
                    }
                }
            }
            chose = 0;
            i++;
        }while(oc1 != 5 || oc2 != 5);
        System.out.println("\n\n\tVoo Cheio Boa decolagem !!\n");
        System.out.println("+___________________________________________+");
        System.out.printf("|Poltrona:    %-2d %-2d %-2d %-2d %-2d %-2d %-2d %-2d %-2d %-2d |\n",1,2,3,4,5,6,7,8,9,10);
        System.out.printf("|Passageiros: ");
        for (int j =0; j < 10; j++){
            System.out.printf("%-2d ", ocup[j]);
        }
        System.out.println("|");
        System.out.println("+-------------------------------------------+");
        System.out.println("\n\tEND!\n");
    }
}
