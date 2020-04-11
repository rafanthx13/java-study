/*
6.35 (Computer-Assisted Instruction) The use of computers in education is referred to as computer-
assisted instruction (CAI). Write a program that will help an elementary school student learn
multiplication.
    Use a SecureRandom object to produce two positive one-digit integers. The program
should then prompt the user with a question, such as
    How much is 6 times 7?
The student then inputs the answer. Next, the program checks the student’s answer. If it’s correct,
display the message "Very good!" and ask another multiplication question. If the answer is wrong,
display the message "No. Please try again." and let the student try the same question repeatedly
until the student finally gets it right. A separate method should be used to generate each new question.
This method should be called once when the application begins execution and each time the
user answers the question correctly.
 */


import java.security.SecureRandom;
import java.util.Scanner;
public class Question35 {

    static int acertos = 0, qtd = 0, lv = 1;
    static Scanner input = new Scanner(System.in);
    static SecureRandom secR = new SecureRandom();

    public static void generate_question(){
        int n1, n2, result, answer, showing;
        n1 = Question35.secR.nextInt(10);
        n2 = Question35.secR.nextInt(10);
        result = n1 * n2;
        do {
            System.out.printf("How much is %d * %d\n==> ", n1, n2);
            answer = Question35.input.nextInt();
            if(answer == result){
                Question35.acertos++;
                Question35.qtd++;
                showing = 1 + Question35.secR.nextInt(4);
                switch (showing){
                    case 1:
                        System.out.println("Very good!");
                        break;
                    case 2:
                        System.out.println("Excellent");
                        break;
                    case 3:
                        System.out.println("Nice work!");
                        break;
                    case 4:
                        System.out.println("Keep up the good work!");
                        break;
                }
                System.out.println();
                break;
            }else{
                Question35.qtd++;
                showing = 1 + Question35.secR.nextInt(4);
                switch (showing){
                    case 1:
                        System.out.println("No. Please try again.");
                        break;
                    case 2:
                        System.out.println("Don't give up!");
                        break;
                    case 3:
                        System.out.println("No. Keep trying.");
                        break;
                    case 4:
                        System.out.println("Wrong. Try once more.");
                        break;
                }
                System.out.println();
            }
        }while (true);
    }

    public static void generate_question_lv2(){
        int n1, n2, result, answer, showing;
        n1 = Question35.secR.nextInt(100);
        n2 = Question35.secR.nextInt(100);
        result = n1 * n2;
        do {
            System.out.printf("How much is %d * %d\n==> ", n1, n2);
            answer = Question35.input.nextInt();
            if(answer == result){
                Question35.acertos++;
                Question35.qtd++;
                showing = 1 + Question35.secR.nextInt(4);
                switch (showing){
                    case 1:
                        System.out.println("Very good!");
                        break;
                    case 2:
                        System.out.println("Excellent");
                        break;
                    case 3:
                        System.out.println("Nice work!");
                        break;
                    case 4:
                        System.out.println("Keep up the good work!");
                        break;
                }
                System.out.println();
                break;
            }else{
                Question35.qtd++;
                showing = 1 + Question35.secR.nextInt(4);
                switch (showing){
                    case 1:
                        System.out.println("No. Please try again.");
                        break;
                    case 2:
                        System.out.println("Don't give up!");
                        break;
                    case 3:
                        System.out.println("No. Keep trying.");
                        break;
                    case 4:
                        System.out.println("Wrong. Try once more.");
                        break;
                }
                System.out.println();
            }
        }while (true);
    }


    static public boolean ask_again(){
        System.out.printf("Digit -1 to EXIT or Other To CONTINUE\n\t==> ");
        int ask =  Question35.input.nextInt();
        if(ask == -1){
            return false;
        }else{
            return true;
        }
    }

    public static boolean relatorio(){
        System.out.println("Relatorio de Rendimento:");
        System.out.printf("\tNumero de tentativas: %d\n", Question35.qtd);
        System.out.printf("\tNumero de Acertos: %d\n", Question35.acertos);
        double porcentagem = ((double) Question35.acertos/ Question35.qtd );
        System.out.printf("\tPorcetagem de Acertos: %.2f\n\n", porcentagem);
        if(porcentagem >= 0.75){
            System.out.println("\tCongratulations, you are ready to go to the next level");
            Question35.lv++;
            return true;
        }else {
            System.out.println("\tPlease ask your teacher for extra help");
            return false;
        }
    }

    /*
    the percentage is lower than 75%, display "Please ask your teacher for extra help.",
then reset the program so another student can try it. If the percentage is 75% or higher, display
"Congratulations, you are ready to go to the next level!", then reset the program so another
student can try it.
     */


    public static void main(String[] args) {
        int i = 1;

        System.out.println("Exercicios de Multiplicação LV1\n");
        do{
            System.out.printf("\n\tQ%d\n", i);
            generate_question();
            i++;
            if(Question35.qtd > 10){
                if(Question35.relatorio() == true)
                    break;
            }
        }while(ask_again() == true);

        if(Question35.lv == 2){
            i = 0;
            System.out.println("\nExercicios de Multiplicação LV2\n");
            do{
                System.out.printf("\n\tQ%d\n", i);
                generate_question_lv2();
                i++;
                if(Question35.qtd > 20){
                    if(Question35.relatorio() == true)
                        break;
                }
            }while(ask_again() == true);

        }
        System.out.println("\nEND\n");
    }

}
