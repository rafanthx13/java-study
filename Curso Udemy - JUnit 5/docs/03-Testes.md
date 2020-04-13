# Testes

## Links
+Aula do Curso : "Design de API's REstful com SpringBoot Tdd e JUnit5"

## POrque testar o código

+ Diminui a chance de bugs
+ Testes de Regressão
  - Caso vocÊ crie uma funcionalidade, será que issp vai bugar em algum outro lugar?
+ Pois desenvolvedores deixam apssar erros
+ Refatoração segura
+ Melhora seu código
  - Garante que qualquer erro seja capturado e de que haja cenários para o máximo de erros possíveis que nâo travem a aplicação
+ As emrpesas exigem que vocÊ testes os códigos

**DESCULPAS PARA NÂO TESTAR**

+ "Meu código está rolando perfeitamnete": Seŕios?
+ "Testar é confuso e complicado": Se vocÊ consegue testar entâo vocÊ relamente entende o código
+ "Aumenta o tempo de senvolviemnto": No início sim, mas quando você se habituar, se vocÊ desevolve suites de testes, cada vez que vocE desenvolve novas funcionaldiade vocÊ vai pdoer gerar tudo rapidamente em vez de testar tudo manualmente a toda hora

## Tipos de Testes

### Teste Unitário

Menor parte testável de um programa. Os módulos sâo testados separadamente. Isolamos a classe que estamos testando e simulamos diversos comportamtenos 

### Testes de integraçâo

Tetsa a integraçâo entre duas partes de um isstema: tesde dde comunicaçâo como: web,  sockets eler/escrever arquivos de textos. Eu verifica se minha aplicaçâo se integra bem com eles

### COmo é a estrutura de testse

1. Cenário
2. Execuçao
3. Verificação


Exemplo:

````
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
````

## TDD (Test Driven Development)

É desenvolver o teste primeiro e depois impleementar a funcionaldiae:

FLuxo:
Escrever o teste que começa falahnaod;
Implementa a funcionaldiade
Refatora

## BDD (Behavior Driven Development)

Escreva tetes que quaqleur humano possa ler. Escreve os testes antes mas tambme agente escerver toda a funcionalidaade do sistema. Traz um estilo de escreevr os testse de forma ammigável.

Beneficis

+ melhor legibiliaddae e em consegeuncia, manutenibilidade dos tests
+ sesus testse são a documentação. Se novos comportamtneos forem escreito obrigatoriamente os testes terao que ser reescritos. Assim o testes passa a ser uma epsecie de documentaçâo viva e atual

Fluxo
: given : when  : then
: dado  : quando: entao 

# Libs

#### AssertionsJ

link: https://joel-costigliola.github.io/assertj/assertj-core-quick-start.html

Possui mais assertivas que o JUnit padrâo. Podem ser bem úteis, para se evitar fazer todo um tratamento par aficar de um formato que o JUnit pegue.

````
<dependency>
  <groupId>org.assertj</groupId>
  <artifactId>assertj-core</artifactId>
  <!-- use 2.9.1 for Java 7 projects -->
  <version>3.11.1</version>
  <scope>test</scope>
</dependency>
````


#### JUnit v4

````
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
    <scope>test</scope>
</dependency>
````

#### Mokcito

````
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>3.3.3</version>
</dependency>
````
