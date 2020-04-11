/**
 * Created by Rafael on 15/04/2017.
 */

/*
Atr: nome, sobrenome,, data nasc, construtor que recebe, getter e setteeres
metdod calcular idade = anos
metodo q calcula e retorna a freq maxima
metodo q retorna a freq card do alvo/pessoa

freq maxima = 220 - idadePessoa
freq alvo = entre 50 a 85% da freq maxima

 */

// Vamos considerar o ano sempre 2017
public class Question16_HeartRates {

    private String nome;
    private String sobrenome;
    private int dia;
    private int mes;
    private int ano;

    // Construtor

    public  Question16_HeartRates(String nome, String sobrenome, int dia, int mes, int ano){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    //Metodos

    public int idadePessoa(){
        return 2017 - this.ano;
    }

    public int freqMax(){
        return 200 - this.idadePessoa();
    }

    public double freqAlvo1(){
        return ((double)freqMax())*(0.5);
    }

    public double freqAlvo2(){
        return ((double)freqMax())*(0.85);
    }



    // Getters e Setters



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
