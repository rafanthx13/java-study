package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirException;

public class Calculadora {


    public int soma(int a, int b) {
        System.out.println("Imprime algo aqui");
        return a + b;
    }

    public int subtracao(int a, int b){
        return a - b;
    }

    public double dividir(int a, int b) throws NaoPodeDividirException {
        if(b == 0){
            throw new NaoPodeDividirException();
        }
        return a / b;
    }

    public void imprime(){
        System.out.println("Algo pasosu por aqui!");
    }
}
