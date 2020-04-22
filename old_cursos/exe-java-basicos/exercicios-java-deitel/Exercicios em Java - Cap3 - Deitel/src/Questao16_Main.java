/**
 * Created by Rafael on 15/04/2017.
 */
import java.util.Scanner;
public class Questao16_Main {

    public static void main(String[] args){
        int dia, mes, ano;
        String nome, sobrenome;

        Scanner input = new Scanner(System.in);

        System.out.println("Programa de Frequencia Cardiaca:\n");
        System.out.println("Digite seus dados:");
        System.out.print("Nome: ");
        nome = input.nextLine();
        System.out.print("Sobrenome: ");
        sobrenome = input.nextLine();
        System.out.print("Dia: ");
        dia = input.nextInt();
        System.out.print("Mes: ");
        mes = input.nextInt();
        System.out.print("Ano: ");
        ano = input.nextInt();

        Question16_HeartRates pessoa = new Question16_HeartRates(nome, sobrenome, dia, mes, ano);

        System.out.println("");
        System.out.println("\n\nSeus dados Digitados:");
        System.out.printf("\tNome: %s\n", pessoa.getNome());
        System.out.printf("\tSobrenome: %s\n", pessoa.getSobrenome());
        System.out.printf("\tData de Nascimento: %d/%d/%d%n", pessoa.getDia(), pessoa.getMes(), pessoa.getAno());
        System.out.printf("\tIdade: %d%n%n", pessoa.idadePessoa());
        System.out.println("Dados Cardiacos:");
        System.out.printf("\tFrequencia cardiaca Maxima: %d\n", pessoa.freqMax());
        System.out.printf("\tFrequencia Normal: Between %3.2f and %3.2f\n", pessoa.freqAlvo1(), pessoa.freqAlvo2());




    }


}


