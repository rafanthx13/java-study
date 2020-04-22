/**
 * Created by Rafael on 04/05/2017.
 */

/*  Question 17 - Chapter 7
7.17 (Dice Rolling) Write an application to simulate the rolling of two dice. The application
should use an object of class Random once to roll the first die and again to roll the second die. The
sum of the two values should then be calculated. Each die can show an integer value from 1 to 6, so
the sum of the values will vary from 2 to 12, with 7 being the most frequent sum, and 2 and 12 the
least frequent. Figure 7.28 shows the 36 possible combinations of the two dice. Your application
should roll the dice 36,000,000 times. Use a one-dimensional array to tally the number of times
each possible sum appears. Display the results in tabular format.
 */

import java.util.Arrays; // Para usar metodo fill
import java.util.Random;
public class Question17 {



    public static void main(String[] args) {

        int VEC_SIZE = 36000000;
        int a, b, x;
        Random rand = new Random();
        int show_dice[] = new int [12]; // vamos guarda a soma em kd parte de [1] = 2 há [11] = 12
        Arrays.fill(show_dice,0);
        for(int i = 0; i < VEC_SIZE;i++){
            a = 1 + rand.nextInt(6); //bound 6 pois, é como vetor, vai de 0 á n-1
            b = 1 + rand.nextInt(6);
            x = a + b;
            show_dice[x-1]++;
        }
        System.out.println("Tabela de 2 dados jogados em 36 milhoes de vezes:\n");
        System.out.printf("  +     1       2       3       4       5       6\n");
        System.out.printf("  1: %-8d%-8d%-8d%-8d%-8d%-8d\n", show_dice[1],show_dice[2], show_dice[3], show_dice[4], show_dice[5], show_dice[6]);
        System.out.printf("  2: %-8d%-8d%-8d%-8d%-8d%-8d\n", show_dice[2],show_dice[3], show_dice[4], show_dice[5], show_dice[6], show_dice[7]);
        System.out.printf("  3: %-8d%-8d%-8d%-8d%-8d%-8d\n", show_dice[3],show_dice[4], show_dice[5], show_dice[6], show_dice[7], show_dice[8]);
        System.out.printf("  4: %-8d%-8d%-8d%-8d%-8d%-8d\n", show_dice[4],show_dice[5], show_dice[6], show_dice[7], show_dice[8], show_dice[9]);
        System.out.printf("  5: %-8d%-8d%-8d%-8d%-8d%-8d\n", show_dice[5],show_dice[6], show_dice[7], show_dice[8], show_dice[9], show_dice[10]);
        System.out.printf("  6: %-8d%-8d%-8d%-8d%-8d%-8d\n", show_dice[6],show_dice[7], show_dice[8], show_dice[9], show_dice[10], show_dice[11]);
        System.out.println();
    }
}
