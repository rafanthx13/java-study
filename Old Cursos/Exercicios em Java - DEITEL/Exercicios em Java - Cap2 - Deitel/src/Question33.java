/**
 * Created by Rafael on 12/04/2017.
 */
import java.util.Scanner;
public class Question33 {

    /*Questao2.33 (IMC = indice de Massa Corporal) Crie um programa que calcule o IMC  de forma
        IMC = Peso(KG)/ Altura^2
      Em  seguida diga se esta ou nao acima do peso
     */

    public static void main(String[] args){

        float altura, peso, imc;
        Scanner gear_input = new Scanner(System.in);
        System.out.print("Digite seu peso em KG: ");
        peso = gear_input.nextFloat();
        System.out.print("Digite a altura em m: (Usando virgula) ");
        altura = gear_input.nextFloat();
        imc = peso/(altura*altura);



        System.out.print("\nBMI VALUES\n");
        System.out.print("  --> Underweight: less than 18.5\n");
        System.out.print("  --> Normal:      between 18.5 and 24.9\n");
        System.out.print("  --> Overweight:  between 25 and 29.9\n");
        System.out.print("  --> Obese:       30 or greater\n");

        System.out.print("\n\n");

        if(imc < 18.5){
            System.out.print("  --> Underweight: less than 18.5");
        }else if(imc >= 18.5 && imc < 25){
            System.out.print("  --> Normal:      between 18.5 and 24.9");
        }else if(imc >= 25 && imc < 29.9){
            System.out.print("  --> Overweight:  between 25 and 29.9");
        }else if(imc > 30){
            System.out.print("  --> Obese:       30 or greater");
        }

        System.out.printf("IMC = %f", imc);

    }
}
