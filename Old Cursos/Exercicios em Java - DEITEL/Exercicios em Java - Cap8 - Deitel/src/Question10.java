/**
 * Created by Rafael on 15/05/2017.
 */

/*  Question 10 - Chapter 8
8.10 Write an enum type TrafficLight, whose constants (RED, GREEN, YELLOW) take one parameter—
the duration of the light. Write a program to test the TrafficLight enum so that it displays the
enum constants and their durations.
 */

//criaçao de ENUM
public class Question10 {

    public static void main(String[] args) throws InterruptedException {

        System.out.printf("\tQuestao de Semaforo\n\n");

        for(TrafficLight semaforo: TrafficLight.values()){
            System.out.printf("Cor do semaforo: %s\n", semaforo.getCor());
            System.out.printf("Tempo de espera: %d\n\t==> ", semaforo.getTempo());
            for(int i=0; i< semaforo.getTempo(); i++){
                Thread.sleep(1000);
                System.out.printf("+");
            }
            System.out.println("\n");
        }

        System.out.println("END");

    }
}

enum TrafficLight{

    //Criando as constantes (RED,GREEN,YELLOW)
    RED("vermelho", 5),
    YELLOW("amarelo", 2),
    GREEN("verde",8);

    //Campos do ENUM
    private  String cor;
    private int tempo;

    TrafficLight(String cor, int tempo){
        this.cor = cor;
        this.tempo = tempo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
