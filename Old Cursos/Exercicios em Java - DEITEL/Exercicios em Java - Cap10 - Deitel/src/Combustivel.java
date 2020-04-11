/**
 * Created by Rafael on 15/06/2017.
 */
public enum Combustivel {

    PETROLEO("Petroleo",1),
    DIESEL("Disel",3),
    GAS_NATURAL("Gas Natural",2),
    ALCOLL("Alcool",4);


    private final String nome;
    private final int fator_de_consumo;

    Combustivel(String nome, int fat_con){
        this.nome = nome;
        this.fator_de_consumo = fat_con;
    }

    public String getNome() {
        return nome;
    }

    public int getFator_de_consumo() {
        return fator_de_consumo;
    }


}
