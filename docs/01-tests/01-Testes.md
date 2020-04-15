# Testes

## Links
+ Aula do Curso : "Design de API's REstful com SpringBoot Tdd e JUnit5"

## Porque testar o código

+ Diminui a chance de bugs
+ Testes de Regressão
  - Caso você crie uma funcionalidade, será que isso vai bugar em algum outro lugar?
+ Pois desenvolvedores deixam passar erros
+ Refatoração segura
+ Melhora seu código
  - Garante que qualquer erro seja capturado e de que haja cenários para o máximo de erros possíveis que não travem a aplicação
+ As empresas exigem que você testes os códigos

**DESCULPAS PARA NÃO TESTAR**

+ "Meu código está rolando perfeitamente": Sérios?
+ "Testar é confuso e complicado": Se você consegue testar então você relamente entende o código
+ "Aumenta o tempo de desenvolvimento ": No início sim, mas quando você se habituar, se você desenvolve suítes de testes, cada vez que você desenvolve novas funcionalidades você vai poder gerar tudo rapidamente em vez de testar tudo manualmente a toda hora

## Tipos de Testes

### Teste Unitário

Menor parte testável de um programa. Os módulos são testados separadamente. Isolamos a classe que estamos testando e simulamos diversos comportamentos 

### Testes de integração

Testa a integração entre duas partes de um sistema: teste de comunicação como: web, sockets ler/escrever arquivos de textos. Eu verifica se minha aplicação se integra bem com eles

### Como é a estrutura de tests

1. Cenário
2. Execução
3. Verificação


Exemplo:

````java
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

É desenvolver o teste primeiro e depois implementar a funcionalidade:

Fluxo:

1. Escrever o teste que começa falhando;
2. Implementa a funcionalidade
3. Refatora

## BDD (Behavior Driven Development)

Escreva tetes que qualquer humano possa ler. Escreve os testes antes mas também agente escrever toda a funcionalidade do sistema. Traz um estilo de escrever os teste de forma amigável.

Benefícios

+ melhor legibilidade e em consequência, manutenibilidade dos tests
+ Seus tests são a documentação. Se novos comportamentos forem escritos obrigatoriamente os testes terão que ser reescritos. Assim o testes passa a ser uma espécie de documentação viva e atual

|          |            |           |
| -------- | ---------- | --------- |
| given    | when       | then      |
| dodo ... | quando ... | então ... |

# Libs

#### AssertionsJ

link: https://joel-costigliola.github.io/assertj/assertj-core-quick-start.html

Possui mais assertivas que o JUnit padrão. Podem ser bem úteis, para se evitar fazer todo um tratamento par aficar de um formato que o JUnit pegue.

````xml
<dependency>
  <groupId>org.assertj</groupId>
  <artifactId>assertj-core</artifactId>
  <!-- use 2.9.1 for Java 7 projects -->
  <version>3.11.1</version>
  <scope>test</scope>
</dependency>
````


#### JUnit v4

````xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
    <scope>test</scope>
</dependency>
````

#### Mockito

````xml
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>3.3.3</version>
</dependency>
````
