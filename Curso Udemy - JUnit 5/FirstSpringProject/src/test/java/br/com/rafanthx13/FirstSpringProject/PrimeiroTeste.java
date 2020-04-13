package br.com.rafanthx13.FirstSpringProject;

import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;


public class PrimeiroTeste {

  Calculadora calc;
  int numero1 = 10, numero2 = 5;

  @Before
  public void setUp(){
    calc = new Calculadora();
  }

  @Test
  public void deveSomarDoisNumeros() {
    Calculadora calc = new Calculadora();
    int numero1 = 10, numero2 = 5;
    int resultado = calc.somar(numero1, numero2);
    Assert.assertEquals(15, resultado);
    Assertions.assertThat(resultado).isBetween(4, 44);
  }

  @Test
  public void deveSubtrairDoisNumeros(){
    int resultado = calc.subtrair(numero1, numero2);
    Assertions.assertThat(resultado).isEqualTo(5);
  }

  @Test
  public void deveMultiplicarDoisNumeros(){
    int resultado = calc.multiplicar(numero1, numero2);
    Assertions.assertThat(resultado).isEqualTo(50);
  }

  @Test(expected = ArithmeticException.class)
  public void naoDeveDividirPorZero(){
    int numeroZero = 0;
    calc.dividir(numero1, numeroZero);
  }

}

class Calculadora {

  int somar(int x, int y){
    return x + y;
  }

  int subtrair(int x, int y){
    return x -y;
  }

  int multiplicar(int x, int y){
    return x * y;
  }

  float dividir(int x, int y){
    return x / y;
  }
  
}