package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.exceptions.NaoPodeDividirException;
import br.ce.wcaquino.runs.PararelRun;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

// Para execuar em paralelo, eh o java que controla essas threads
//@RunWith(PararelRun.class)
public class CalculadoraTest {

    /*  Para uma var se usada por todos os teste, ela deverá
            ser global, ou seja, uma var da classe de TEST
     */
    @Mock
    private Calculadora calc;

    @Spy
    private Calculadora calcSpy;

    @Before
    public void setup(){
        System.out.println("before for view pararel");
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void method_before(){
        calc = new Calculadora();
    }

    @After
    public void method_after(){
        System.out.println("after for view pararel");
    }


    @Test
    public void somarValores(){
        //cenario
        int a = 5;
        int b = 3;
        Calculadora calc = new Calculadora();

        //açao
        int resultado = calc.soma(a,b);

        //resultado
        Assert.assertEquals(8, resultado);


    }

    @Test
    public void subtrairTest(){
        //cenario
        int a = 5;
        int b = 3;
        Calculadora calc = new Calculadora();

        //açao
        int resultado = calc.subtracao(a,b);

        //resultado
        Assert.assertEquals(2, resultado);
    }

    @Test
    public void dividirTest() throws NaoPodeDividirException {
        //cenario
        int a = 6;
        int b = 3;
        Calculadora calc = new Calculadora();

        //açao
        double resultado = calc.dividir(a,b);

        //resultado
        Assert.assertEquals(2.0, resultado, 0.01);
    }

    @Test(expected = NaoPodeDividirException.class)
    public void dividirZeroTest() throws NaoPodeDividirException {
        //cenario
        int a = 6;
        int b = 0;
        Calculadora calc = new Calculadora();

        //açao
        double resultado = calc.dividir(a,b);

        //resultado
        Assert.assertEquals(2.0, resultado, 0.01);
    }

    @Test
    public void teste_matcher(){
        Calculadora calculadora = Mockito.mock(Calculadora.class);
        Mockito.when(calculadora.soma(Mockito.eq(1),Mockito.anyInt())).thenReturn(5);
        System.out.println(calculadora.soma(1,7));
    }

    /*  5.9 - Argument Captor
        + voce pode capturar na fase de verificaçao
        + Neses teste, o argumentCapor é meio como um matcher, mas,
                tenho o controle do que é passado
     */
    @Test
    public void teste_argCaptor(){
        // Criamos o mock da calculadora
        Calculadora calc = Mockito.mock(Calculadora.class);

        // Criando um capturar de param, ele ira pegar Integer e colocalo numa lista desse tipo
        ArgumentCaptor<Integer> argCap = ArgumentCaptor.forClass(Integer.class);

        /*
            + Estamos dizendo que? quando o objetoMock calc executar essa soma
            + Esses argumentos serao capturados pelo argCapter e armazendaos dentro dele
                Alem disso, vai retornar true
            + Da forma que esta construido, o argCap funciona como um macher pra qualquer coisa
            + No assert, envocamos a execuçao desse mock:
                1. Como dizemos que retorna 5, ele vai retornar 5
                2. COmo dizemos pra capturar esse valores, entao, eles sao capturados e armazendaos
                    me ArgCaptor
                3. Asism, dá certo, pois espera 5 e colocamos para o mock voltar 5
            + o argCap.getValue() retorna o ultimo valor lido
            + se eu quero mostra mais deu um, executo o argCap.getAllValues(), terá nesse caso
                pois chamamos duas vezes o captor
        */
        Mockito.when(calc.soma(argCap.capture(), argCap.capture())).thenReturn(5);

        Assert.assertEquals(5, calc.soma(1,1000));
        System.out.println(argCap.getValue());
        System.out.println(argCap.getAllValues());
    }

    /*  3.10 - Spy
        + Diferenças entre mock e spy

     */
    @Test
    public void mostrarDiferencaMockSpy(){
        Mockito.when(calc.soma(1,2)).thenReturn(4);
        //Mockito.when(calcSpy.soma(1,2)).thenReturn(4);
        Mockito.doReturn(5).when(calcSpy).soma(1,2);
        // Com isso nao o spy nao executa o método void
        Mockito.doNothing().when(calcSpy).imprime();

        System.out.println("Mock " + calc.soma(3,4));     // Mock - defaul:0
        System.out.println("Spy " + calcSpy.soma(1,2)); //  Spy  - default: real

        calc.imprime();     // nao vai executar void, por def.
        calcSpy.imprime(); //vai executar método void
    }


}
