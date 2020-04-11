package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.ce.wcaquino.dao.LocacaoDAO;
import br.ce.wcaquino.exceptions.FilmeInexistenteException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.runs.PararelRun;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import org.mockito.Mockito;

/*
    2.18 - Significa que vamos rodar esse teste como um Parameterized
    LINKS:
    https://www.tutorialspoint.com/junit/junit_parameterized_test.htm
    http://junit.org/junit4/javadoc/4.12/org/junit/runners/Parameterized.html

 */

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

    private LocacaoService service;

    /*  // OS PARAMETRO DEVEM SER PUBLICOS
      Aqui vamos declarar QUAIS E COMO SAO OS PARAMETROS DE ENTRADA
            param0 = lista de filme
            param1 = valor da locaçao
            param2 = O nome do cenario (para mostrar)
     */

    // Esse sera o paramentro [0] - O primerio nao presisa declarar
    @Parameter(value = 0)
    public List<Filme> filmes;

    // Esse sera o paramentro [1] - Eh necessario declarar
    @Parameter(value=1)
    public Double valorLocacao;

    // Esse sera o paramentro [2] - Eh necessario declarar
    @Parameter(value=2)
    public String cenario;

    @Before
    public void setup(){
        LocacaoDAO locacaoDAO = Mockito.mock(LocacaoDAO.class);
        SPCService spcService = Mockito.mock(SPCService.class);
        service = new LocacaoService();
        service.setLocacaoDAO(locacaoDAO);
        service.setSPCService(spcService);
    }

    // Para usar o parametrized, as variaveis tem que serrem staticas
    private static Filme filme1 = new Filme("Filme 1", 2, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 2, 4.0);
    private static Filme filme7 = new Filme("Filme 7", 2, 4.0);

    /*
        + Esses sao os parametros de entrada, eh uma matriz quadrado 2x2 com uma coleçao de objetos
            onde que, exeecutara os testes com esse parametor,
        + Eles devem ser declarados com @Parameter e com seu valor (value = num)
        + O metodo de Parameters pode ter qualquer nome
        + o nome do metodo de @Parameters pode ser qualquer um
     */

    @Parameters(name="{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][] {
                //[param0 - lista de filme, param1 - valor da locaao, param2 - msg do teste]
                //[         [0]filmes     ,         [1]valorLocacao ,  [2]cenario          ]
                {Arrays.asList(filme1, filme2), 8.0, "2 Filmes: Sem Desconto"},
                {Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes: 25%"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 Filmes: 50%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes: 100%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 Filmes: Sem Desconto"}
        });
    }

    /*
        O teste será executado n veses, sendo n o numero de linhas do @Parameters, onde usará
        as varaiveis dessa linha, onde cada uma delas representará o @Parameter[x] na mesma ordem
            Assim, executará 6 vezes

        CONCLUSAO:
            + Como sempre, a execuçao dos teste eh aleartoria, porem, nao sei por que mas
                os resultados foram apresentadas na ordem correta
            + De preferencia, que nao sejam feito teste dependentes uns com os outros
     */
    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeInexistenteException, FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");


        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(resultado.getValor(), is(valorLocacao));

        //confirmacao que executou direito
        System.out.println(valorLocacao);
    }
}