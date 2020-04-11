import java.util.Scanner;

/**
 * Created by Rafael on 15/05/2017.
 */

/*  Question 15 - Chapter 8
8.15 (Rational Numbers) Create a class called Rational for performing arithmetic with fractions.
Write a program to test your class. Use integer variables to represent the private instance variables
of the class—the numerator and the denominator. Provide a constructor that enables an object of
this class to be initialized when it’s declared. The constructor should store the fraction in reduced
form.
    The fraction
        2/4
    is equivalent to 1/2
and would be stored in the object as 1 in the numerator and 2 in the denominator.
Provide a no-argument constructor with default values in case no initializers are provided. Provide
public methods that perform each of the following operations:

a) Add two Rational numbers: The result of the addition should be stored in reduced
form. Implement this as a static method.

b) Subtract two Rational numbers: The result of the subtraction should be stored in reduced
form. Implement this as a static method.

c) Multiply two Rational numbers: The result of the multiplication should be stored in
reduced form. Implement this as a static method.

d) Divide two Rational numbers: The result of the division should be stored in reduced
form. Implement this as a static method.

e) Return a String representation of a Rational number in the form a/b, where a is the
numerator and b is the denominator.

f) Return a String representation of a Rational number in floating-point format. (Consider
providing formatting capabilities that enable the user of the class to specify the
number of digits of precision to the right of the decimal point.)

 */


import java.util.Scanner;
public class Question15 {

    static public void menu(Rational x, Rational y, Rational z){
        System.out.println("\t\t_____________");
        System.out.println("\t\tMENU RACIONAL\n");
        System.out.printf("\t\t-------------\n");
        System.out.println("\t\t     STATUS\n   N1 = "+ Rational.toString(x) + "   N2 = " + Rational.toString(y) + "   N3 = " + Rational.toString(z));
        System.out.println("\t 1 - Adicao de Racionais");
        System.out.println("\t 2 - Subtracao de Racionais");
        System.out.println("\t 3 - Multiplicacao de Racionais");
        System.out.println("\t 4 - Divisao de Racionais");
        System.out.println("\t 5 - Representacao em float");
        System.out.println("\t 6 - Insert N1");
        System.out.println("\t 7 - Insert N2");
        System.out.println("\t 0 - EXIT!");
        System.out.printf("\t  ==> ");
    }


    public static void main(String[] args) {

        int x,y,op;
        Scanner input = new Scanner(System.in);

        Rational num1 = new Rational();
        Rational num2 = new Rational();
        Rational num3 = new Rational();

        System.out.println("\tQuestao de Expressar Frasao:\n");

        do{
            System.out.printf("Numero 1: Digite o numerador\n\t==> ");
            x = input.nextInt();
            System.out.printf("Numero 1: Digite o denominador\n\t==> ");
            y = input.nextInt();
        }while (!(num1.setNumerador(x) && num1.setDenominador(y)));
        Rational.simplex(num1);

        do{
            System.out.printf("Numero 2: Digite o numerador\n\t==> ");
            x = input.nextInt();
            System.out.printf("Numero 2: Digite o denominador\n\t==> ");
            y = input.nextInt();
        }while (!(num2.setNumerador(x) && num2.setDenominador(y)));
        Rational.simplex(num2);

        do{
            Question15.menu(num1,num2,num3);
            op = input.nextInt();

            if(op == 1){
                num3 = Rational.add_racional(num1,num2);
                Rational.simplex(num3);
            }
            if(op == 2){
                num3 = Rational.sub_racional(num1,num2);
                Rational.simplex(num3);
            }
            if(op == 3){
                num3 = Rational.mul_racional(num1,num2);
                Rational.simplex(num3);
            }
            if(op == 4){
                num3 = Rational.div_racional(num1, num2);
                Rational.simplex(num3);
            }
            if(op == 5){
                System.out.printf("Digite qual racional a representar, 1,2 ou 3:\n\t==> ");
                op = input.nextInt();
                if (op == 1){
                    System.out.println(Rational.express_racional(num1));
                }else if (op == 2){
                    System.out.println(Rational.express_racional(num2));
                }else if (op == 3){
                    System.out.println(Rational.express_racional(num3));
                }else{
                    System.out.println("Digitou numero errado");
                    op = -1;
                }
            }

            if(op == 6) {
                do {
                    System.out.printf("\nDigite o NEW numerador\n\t==> ");
                    x = input.nextInt();
                    System.out.printf("Digite o NEW denominador\n\t==> ");
                    y = input.nextInt();
                } while (!(num1.setNumerador(x) && num1.setDenominador(y)));
                Rational.simplex(num1);
            }

            if(op == 7){
                do{
                    System.out.printf("\nDigite o NEW numerador\n\t==> ");
                    x = input.nextInt();
                    System.out.printf("Digite o NEW denominador\n\t==> ");
                    y = input.nextInt();
                }while (!(num2.setNumerador(x) && num2.setDenominador(y)));
                Rational.simplex(num2);
            }

        }while (op != 0);
        System.out.println("END!");

    }
}

class Rational{

    //STATIC METODOS ---------------------------------------------------------------------------------------------------

    public static Rational add_racional(Rational num1, Rational num2){
        int mmc = Rational.mmc(num1.getDenominador(), num2.getDenominador());
        int denominador = mmc;
        int numerador =  (num1.getNumerador()*(mmc/num1.getDenominador())) + (num2.getNumerador()*(mmc/num2.getDenominador()));
        Rational out_put = new Rational(numerador,denominador);
        Rational.simplex(out_put);
        return out_put;
    }

    public static Rational sub_racional(Rational num1, Rational num2){
        int mmc = Rational.mmc(num1.getDenominador(), num2.getDenominador());
        int denominador = mmc;
        int numerador =  (num1.getNumerador()*(mmc/num1.getDenominador())) - (num2.getNumerador()*(mmc/num2.getDenominador()));
        Rational out_put = new Rational(numerador,denominador);
        Rational.simplex(out_put);
        return out_put;
    }

    public static Rational mul_racional(Rational num1, Rational num2){
        int numerador = num1.getNumerador() * num2.getNumerador();
        int denominador = num1.getDenominador() * num2.getDenominador();
        Rational out_put = new Rational(numerador,denominador);
        Rational.simplex(out_put);
        return out_put;
    }

    public static  Rational div_racional(Rational num1, Rational num2){
        int numerador = num1.getNumerador() * num2.getDenominador();
        int denominador = num1.getDenominador() * num2.getNumerador();
        Rational out_put = new Rational(numerador,denominador);
        Rational.simplex(out_put);
        return out_put;
    }

    public static double express_racional(Rational z){
        return ((double)z.getNumerador()/z.getDenominador());
    }

    public static int mdc(int a, int b) {
        int q, r;
        while (b != 0){
            q = a / b;
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static int mmc(int num1, int num2){
        int resto, a, b;
        a = num1;
        b = num2;
        do {
            resto = a % b;
            a = b;
            b = resto;
        } while (resto != 0);
        return ( num1 * num2) / a;
    }

    public static String toString(Rational z){
        return z.getNumerador() +"/"+ z.getDenominador();
    }

    public static void simplex(Rational thiss){
        int mdc_comun =  mdc(thiss.getNumerador(), thiss.getDenominador());
        if( mdc_comun != 1){
            thiss.setNumerador(thiss.getNumerador()/mdc_comun);
            thiss.setDenominador(thiss.getDenominador()/mdc_comun);
        }
    }

    // VARIAVEIS -------------------------------------------------------------------------------------------------------

    private int numerador;
    private int denominador;

    //CONSTRUTOR -------------------------------------------------------------------------------------------------------

    Rational(){
        this(1,1);
    }

    Rational(int num){
        this(num,1);
    }

    Rational(int num, int den){
            setDenominador(den);
            setNumerador(num); //Coloca denominador primeiro, pois, se o numerador for 0, automaticamente denominador  <= 1
    }

    // GETTERS E SETTER -------------------------------------------------------------------------------------------------------
    public int getNumerador() {
        return numerador;
    }

    public boolean setNumerador(int numerador) {
        if(numerador == 0){
            this.numerador = numerador;
            this.setDenominador(1);
            return true;
        }else{
            this.numerador = numerador;
            return true;
        }

    }

    public int getDenominador() {
        return denominador;
    }

    public boolean setDenominador(int denominador) {
        if(denominador != 0){
            this.denominador = denominador;
            return true;
        }else
            return false;
    }
}
