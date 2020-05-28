/**
 * Created by Rafael on 24/04/2017.
 */

/*  Question 15 - Chpater 5
(Triangle Printing Program) Write an application that displays the following patterns separately,
one below the other. Use for loops to generate the patterns. All asterisks (*) should be printed
by a single statement of the form System.out.print('*'); which causes the asterisks to print side
by side. A statement of the form System.out.println(); can be used to move to the next line. A
statement of the form System.out.print(' '); can be used to display a space for the last two patterns.
There should be no other output statements in the program. [Hint: The last two patterns require
that each line begin with an appropriate number of blank spaces.]
(a) (b) (c) (d)
* ********** ********** *
** ********* ********* **
*** ******** ******** ***
**** ******* ******* ****
***** ****** ****** *****
****** ***** ***** ******
******* **** **** *******
******** *** *** ********
********* ** ** *********
********** * * **********
 */
public class Question15 {

    public static void main(String[] args) {
        int count, i, j, h;

        System.out.println("Exercicio A\n");

        for( i = 1; i <= 10; i++){
            for(j = 1; j < i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        j = 1;
        System.out.println("\n\nExercicio B\n");
        for(i = 1;i < 10; i++){
            for(h = 10 - i; h >= 0; h--){
                System.out.print(' ');
            }
            for(j = 0; j < i; j++){
                System.out.print("*");
            }
            System.out.println();
        }


        System.out.println("\n\nExercicio C\n");
        for( i = 0; i < 10 ; i++){
            for( j = 0; j < 10 - i; j++){
                System.out.print("*");
            }
            System.out.println();
        }


        System.out.println("\n\nExercicio D\n");
        for(i = 0; i < 10; i++){
            for(h = 9 - i; h >= 0; h--){
                System.out.print("");
            }
            for(j = 0; j < i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
