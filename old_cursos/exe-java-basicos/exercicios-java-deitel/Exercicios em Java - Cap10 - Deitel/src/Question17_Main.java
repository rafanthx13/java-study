/**
 * Created by Rafael on 15/06/2017.
 */

/*
10.17 (Interface CarbonFootprint: polimorfismo) Usando as interfaces, como aprendeu neste capítulo, você pode especificar comportamentos
semelhantes para as classes possivelmente díspares. Governos e empresas em todo o mundo estão cada vez mais preocupados
com as pegadas de carbono (liberações anuais de dióxido de carbono na atmosfera) a partir de edifícios que queimam vários tipos de
combustíveis para aquecimento, veículos que queimam combustíveis para obter energia etc. Muitos cientistas culpam esses gases do efeito
estufa pelo fenômeno chamado de aquecimento global. Crie três classes pequenas não relacionadas por meio de herança — as classes
Building, Car e Bicycle. Dê a cada classe alguns atributos e comportamentos adequados únicos que ela não tem em comum com
outras classes. Escreva uma interface de CarbonFootprint com um método getCarbonFootprint. Faça com que cada uma das
suas classes implemente essa interface para que o método getCarbonFootprint calcule uma pegada de carbono adequada para essa
classe (confira alguns sites que explicam como calcular pegadas de carbono). Escreva um aplicativo que cria objetos de cada uma das três
classes, insere referências a esses objetos em ArrayList<CarbonFootprint>, então itera pelo ArrayList polimorficamente invocando
o método getCarbonFootprint de cada objeto. Para cada objeto, imprima algumas informações de identificação e a pegada de
carbono do objeto.
 */

/*  OBS: Rafael
    Fiz de modo bem grosseiro, somente pra mostra o aproveitamento de polimorfismo em interface
        e para mostra uma unitlizaçao de ENUM pois eu nunca a usei.
 */
class Building implements CarbonFootPrint{
    private String rua;
    private String energia_gasta_WH;
    private int qtd_employe;

    public Building(int qtd_employe) {
        this.qtd_employe = qtd_employe;
    }

    public void construir(){
        System.out.println("Predio COnstruido");
    }

    public void demolir(){
        System.out.println("Predio Demolido");
    }

    @Override
    public double get_CarbonFootPrint() {
        return qtd_employe*CarbonFootPrint.fator_humano;
    }
}




class Car implements CarbonFootPrint{
    private String motor;
    private Combustivel combustivel;

    public Car(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    @Override
    public double get_CarbonFootPrint() {
        return (double)combustivel.getFator_de_consumo();
    }
}




class Bicycle implements CarbonFootPrint{
    private String marca;

    @Override
    public double get_CarbonFootPrint() {
        return 0;
    }
}



public class Question17_Main {

    public static void main(String[] args) {


        CarbonFootPrint[] array_emissor = new CarbonFootPrint[3];
        array_emissor[0] = new Bicycle();
        array_emissor[1] = new Car(Combustivel.PETROLEO);
        array_emissor[2] = new Building(40);
        System.out.println("Consumo de carbono\n");
        for(CarbonFootPrint x: array_emissor){
            System.out.print(x.getClass().getName()+" ");
            System.out.println(x.get_CarbonFootPrint());
        }
        System.out.println("\nEND");
    }
}
