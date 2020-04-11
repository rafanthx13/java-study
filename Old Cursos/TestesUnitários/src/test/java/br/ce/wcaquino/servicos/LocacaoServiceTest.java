package br.ce.wcaquino.servicos;

import br.ce.wcaquino.builder.LocacaoBuilder;
import br.ce.wcaquino.dao.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeInexistenteException;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.runs.PararelRun;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;
import java.util.*;

import static br.ce.wcaquino.builder.FilmeBuilder.umFilme;
import static br.ce.wcaquino.builder.FilmeBuilder.umFilmeSemEstoque;
import static br.ce.wcaquino.builder.UsuarioBuilder.umUsuario;
import static br.ce.wcaquino.matchers.MatchersProprios.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(LocacaoService.class)
/* O Poquer Mock faz muitas coisas por baixo dos panos, por
    isso, é necessa´rio que ele prepare as classe que estao sendo
    bruscamente modificadas(mexer no constutor,
    em metodos static/private e etc...
 */
//@RunWith(PararelRun.class)
public class LocacaoServiceTest {

    //definindo contador
    public static int cont = 0;
    @Rule
    public ErrorCollector errorColetor = new ErrorCollector();
    //Para a Strategy Novo
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks @Spy
    private LocacaoService service;

    @Mock
    private LocacaoDAO locacaoDAO;
    @Mock
    private SPCService spc;
    @Mock
    private EmailService emailService;

    private Filme filme1;
    private Filme filme2;
    private Filme filme3;

    // Chamado antes de instanciar a classe, o metodo deve static ora fazre isso
    @BeforeClass
    public static void method_BeforeClass() {

        //System.out.println("Before Class!");
    }

    // Chamado depois de instanciar a classe, o metodo deve static ora fazre isso
    @AfterClass
    public static void method_AfterClass() {

        //System.out.println("After Class!");
    }

    // Chamado sempre antes de executar qualquer teste
    @Before
    public void method_beforeTest() {
        service = new LocacaoService();
        cont++;
        //System.out.println(cont);
        filme1 = new Filme("A hora do pesadelo", 2, 25.00);
        filme2 = new Filme("Shiganchina", 3, 60.00);
        filme3 = new Filme("Akashi", 4, 15.00);
        // De fake Objetc usaremos Mockito
        //LocacaoDAO locacaoDAO = new LocacaoDAOFake();
        // Crei instanciaas mockadas
        //Com o modo anottaitons - antes de cada teste, vai mockar as coisas e colocalas no @Inject Mocks
        MockitoAnnotations.initMocks(this);

        /*  Modo antigo de Mokcar sem o Annotations 5.7
        locacaoDAO = Mockito.mock(LocacaoDAO.class);
        emailService = Mockito.mock(EmailService.class);
        spc = Mockito.mock(SPCService.class);
        //Tenho que mandar essas instancias para o service, onde vai ser feito o trabalho
        service.setLocacaoDAO(locacaoDAO);
        service.setSPCService(spc);
        service.setEmailService(emailService);
        */

        //service = PowerMockito.spy(service);
        System.out.println("iniciou2");
    }

    // Chamado depois de executar qualquer teste
    @After
    public void method_afterTest() {
        System.out.println("finalizou 2");
        //System.out.println("After!");
    }

    @Test
    public void deveAlugarFilme() throws Exception {
        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilme().valorParam(5.0).agora());

        // dessa forma, vai executar mandar o Date, como um contrutor, e isso foi possivel por causa
        // que colocamos dentro de outro método
        Mockito.doReturn(DataUtils.obterData(28,4,2017)).when(service).obterData();


        // Power Mock
        // PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(DataUtils.obterData(28, 4, 2017));
        /*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 28);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.YEAR, 2017);
        PowerMockito.mockStatic(Calendar.class);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
        */
        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        errorColetor.checkThat(locacao.getValor(), is(equalTo(5.0)));
//		error.checkThat(locacao.getDataLocacao(), ehHoje());
//		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
        errorColetor.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.obterData(28, 4, 2017)), is(true));
        errorColetor.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterData(29, 4, 2017)), is(true));
    }

    @Test
    // Foi tao modificado que nem sem mais o que ele faz
    public void deveAlugarFilmeOld() throws Exception, LocadoraException {
        // Qaundo o dia for sabado, nao sera executado
        // Eu assumo que nao eh sabado, assim,
        //      sera executado quando nao for sabado
        //      nao sera executada quando for sabado, vamo tirala para o powerMock
        //Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        Mockito.doReturn(DataUtils.obterData(28,4,2017)).when(service).obterData();

        //cenario - inicializar as coisas, vamos criar um usuario e filme
        /*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.YEAR, 2017);
        PowerMockito.mockStatic(Calendar.class);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
        */
        // Aqui vamos usar obuilder
        Usuario usuario = umUsuario().agora();
        List<Filme> filmesOnlyOne = Arrays.asList(umFilme().agora());

        //açao - farei a execuçao do metodo que quero testar
        Locacao locacao = service.alugarFilme(usuario, filmesOnlyOne);

        // resultado - errorColetor - com ele eh possivel continuar mesmo dando erro, (pega varios erros)
        errorColetor.checkThat(locacao.getValor(), is(equalTo(4.0)));

        //errorColetor.checkThat(locacao.getValor(), is(not(6.0)));
        //old
        //errorColetor.checkThat( isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        //old
        //errorColetor.checkThat( isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

        // desafio matcher - pra verificar se a data de devolução ficou pra ser daqui a 1 dia
        //errorColetor.checkThat(locacao.getDataRetorno(), ehHojeComDiferencadeDias(1));
        // desafio matcher - pra verificar se ficou a locaçao ficou na data de Hoje
        //errorColetor.checkThat(locacao.getDataLocacao(), ehHoje());
        errorColetor.checkThat(
                DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.obterData(28,4,2017)), is(true));
        errorColetor.checkThat(
                DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterData(29,4,2017)), is(true));


        // ------------------------------------------------------------------------------------------------------------------ //
        //resultado - assertThat - deixa o codigo mais legível de ser lido
        /*
        assertThat(locacao.getValor(), is(equalTo(25.0)));
        assertThat(locacao.getValor(), is(not(6.0)));
        assertThat( isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat( isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
        */

        /* Dando try/cath e obrigando ao teste ficar fail
        Locacao locacao = null;
        try {
            locacao = service.alugarFilme(usuario,filme);
            // resultado - errorColetor - com ele ehpossivel continuar mesmo dando erro, (pega varios erros)
            errorColetor.checkThat(locacao.getValor(), is(equalTo(25.0)));
            errorColetor.checkThat(locacao.getValor(), is(not(6.0)));
            errorColetor.checkThat( isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
            errorColetor.checkThat( isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

        } catch (Exception e) {
            e.printStackTrace();
            // se tirarmos esse Assert.fail nao passa e nao acusa de teste invalido
            //Assert.fail("Deu erro aqui!");
        }
        */
    }

    // STRATEGY == ELEGANTE
    // SUCESS = EXCEPTION || FAIL ==> NOT EXCEPTION
    /* Quer dizer que espera chegar uma exeçao, e quem vai tratar ela sera o Junit,
        Tem eses nome pois passa toda a responsabilidade ao Junit, de forma que tudo
        mas nao se tem muito controle de rastreio
     */
    @Test(expected = Exception.class)
    public void testLocacao_filmeSemEstoque1() throws Exception, LocadoraException {
        //cenario - inicializar as coisas, vamos criar um usuario e filme
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = new ArrayList<Filme>();
        filmes.add(umFilme().agora());
        filmes.add(umFilme().agora());
        //Usando um builder pra criar algo diferente, o Filme sem estoque
        filme3 = umFilme().semEstoque().agora();
        filmes.add(filme3);

        //açao - farei a execuçao do metodo que quero testar
        Locacao locacao = service.alugarFilme(usuario, filmes);
    }

    // STRATEGY = ROBUSTA
    // SUCESS = EXCEPTION || FAIL ==> NOT EXCEPTION
    /* Mais controle no que deu errado, agente faz um try/cath dentro da ACTION,
        Para proteger contra FALSE/POSITIVE colocamos um Assert.fail(), para que
           se aexeçao nao for pegar, indicar entao que deu falha
     */
    @Test
    public void testLocacao_filmeSemEstoque2() throws LocadoraException {
        //cenario - inicializar as coisas, vamos criar um usuario e filme
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = new ArrayList<Filme>();
        filmes.add(umFilme().agora());
        filmes.add(umFilme().agora());
        // PELOS METODOS
        filme3 = umFilme().semEstoque().agora();
        filmes.add(filme3);

        //açao - farei a execuçao do metodo que quero testar
        try {
            service.alugarFilme(usuario, filmes);
            // Para proteger contra falsoPositivo, que ocorre quando o estoque eh != 0, colocamos um assertFail
            // Pois esperamos entrar no catch, entao, se nao entrar no cath tem que dar fail
            Assert.fail("Deveria ter lançado uma Exeçao");
        } catch (Exception e) {
            // Isso serve pra mostrar o log do erro, deixei comentado pra nao poluir na execuao
            //e.printStackTrace();
            // se ter uma execaço, ese e sera instanciado, e podemos pegar sua mensagem e comprar com esse is()
            Assert.assertThat(e.getMessage(), is("Filme sem Estoque!"));
        }
    }


    //STRATEGY = NOVO
    // SUCESS = EXCEPTION || FAIL ==> NOT EXCEPTION
    /* Nesse caso, queremos que venha uma exeçao, nesse metdoo, criamos uma nova rule chamada exception
        ela deve fazer parte do cenario,eh como se ela ativa-se uma trigger para que se espere uma exeçao
        e neste caso, que chegue tambem a mensagem de filme sem estoque
        ==> OBS: eese exception tem que ficar antes da ACTION, TEM QUE SER PARTE DO SCENARIO
    */
    @Test
    public void testLocacao_filmeSemEstoque3() throws Exception, LocadoraException {
        //cenario - inicializar as coisas, vamos criar um usuario e filme
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = new ArrayList<Filme>();
        filmes.add(umFilme().agora());
        filmes.add(umFilme().agora());
        // Forma do OBJETC MOTHER
        filme3 = umFilmeSemEstoque().agora();
        filmes.add(filme3);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem Estoque!");

        //açao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void devePagar75NoFilme3() throws Exception {
        // cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(
                umFilme().agora(),
                umFilme().agora(),
                umFilme().agora());
        // açao
        Locacao resultado = service.alugarFilme(usuario, filmes);
        // verificaçao
        // 4+4+3 = 13
        Assert.assertThat(resultado.getValor(), is(11.0));
    }

    @Test
    public void devePagar50NoFilme4() throws Exception {
        // cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0),
                new Filme("Filme2", 3, 4.0), new Filme("Filme3", 2, 4.0),
                new Filme("Filme4", 2, 4.0));
        // açao
        Locacao resultado = service.alugarFilme(usuario, filmes);
        // verificaçao
        // 4+4+3+2= 13
        Assert.assertThat(resultado.getValor(), is(13.0));
    }


    @Test
    public void devePagar100NoFilme6() throws Exception {
        // cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0),
                new Filme("Filme2", 3, 4.0), new Filme("Filme3", 2, 4.0),
                new Filme("Filme4", 2, 4.0), new Filme("Filme5", 2, 4.0),
                new Filme("Filme6", 2, 4.0));
        // açao
        Locacao resultado = service.alugarFilme(usuario, filmes);
        // verificaçao
        // 4+4+3+2+1= 13
        Assert.assertThat(resultado.getValor(), is(14.0));
    }

    /*
        Esse teste so vai funcionar quando eu sabado, para que eu entregue no domingo
     */
    @Test
    @Ignore
    public void naoDeveDevolverFilmeNoDomingo() throws Exception {
        // Quando nao for sabado, esse teste nao sera executado
        // Estou dizendo que estou asusmindo que é sabado,
        //      por isso, so vai executar no saibado
        //      nao sera executado no sabado - powerMock
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        // cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        // action
        Locacao retorno = service.alugarFilme(usuario, filmes);

        // Verificação
        boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(ehSegunda);
    }


    @Test
    @Ignore // aqui conseidera Date.class, tem que mudar para Calendar
    /* Só eh executado no sbadao, pois ai espera que a dta de retorno seja na segunda para executar um metodo
        + O asusme serve para ignorar quando nao estiver de acordao com a condiçao (qunado nao for sabado

        6.2 - Power Mock - Mockar Construtor
     */
    public void naoDeveDevolverFilmeNoDomingoWithMatchers() throws Exception {
        // Assume que eh sabado, so aí que vai pra frente
        //Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        // cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(new Filme("Filme 2", 1, 5.0));

        PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(
                DataUtils.obterData(29,4,2017)
        );
        // action
        Locacao retorno = service.alugarFilme(usuario, filmes);

        // Verificação
        // Vamos testar o MATCHER com 3 opções:
        //assertThat(retorno.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
        assertThat(retorno.getDataRetorno(), caiEm(Calendar.MONDAY));
        //assertThat(retorno.getDataRetorno(), caiNumaSegunda());
    }
    // 6.2 - Copiado dele
    @Test
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws Exception{
        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilme().agora());



//		PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(DataUtils.obterData(29, 4, 2017));
        /*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.YEAR, 2017);
        PowerMockito.mockStatic(Calendar.class);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
        */
        //acao
        Mockito.doReturn(DataUtils.obterData(4,11,2017)).when(service).obterData();
        Locacao retorno = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(retorno.getDataRetorno(), caiNumaSegunda());
//		PowerMockito.verifyNew(Date.class, Mockito.times(2)).withNoArguments();

        //PowerMockito.verifyStatic(Mockito.times(2));
        Calendar.getInstance();
    }


    /*   5.5 - O teste flaha pois o spc instanciado pelo mocki retorna por padrao false
            e para entra la tem que retornar true

            + A notação do Mockito.when so funciona sobre o objeto usuario, se  mandarmos
                entao um usuario diferente, ele nao vai aceitar, pois sera uma isntanca com outro nome
                e com outros valores, no qual, o eQuals nao vai reconhecer
     */
    @Test
    public void naoDeveAlugarFilmeParaNegativadoSPC() throws Exception {
        // cenário
        Usuario usuario = umUsuario().agora();
        Usuario usuario2 = new Usuario("Usuario 2");
        List<Filme> filmes = Arrays.asList(umFilme().agora());

        // Linha do Mockito - qualquer usaurio diferente de 'usuario' ele vai retonar false, que dá assim false
        // estamos entao SIMULANDO QUE ESSE METODO EXISTE E QUE RETORNARIA TRUE ==> TA NEGATIVADO
        Mockito.when(spc.possuiNegativacao(Mockito.any(Usuario.class))).thenReturn(true);

        //verifica - lembre-se que a estrategia de NOVO tem que colocar isso antes da ação
        // Mudamos da strategy de Novo para ROBUSTA
        //exception.expect(LocadoraException.class);
        //exception.expectMessage("Usuário Negativado");

        // acao - muda de usuario <---> usuario2 para testa sobre instancias difereten, uma da SUCCESS/FAILED
        try {
            service.alugarFilme(usuario, filmes);
            //garante que, se não entrar nas exeçôes, o teste vai dár falso pois deveria dar falso
            Assert.fail();
        } catch (LocadoraException e) {
            //e.printStackTrace();
            Assert.assertThat(e.getMessage(), is("Usuario Negativado"));
        } catch (FilmeSemEstoqueException e) {
            e.printStackTrace();
        } catch (FilmeInexistenteException e) {
            e.printStackTrace();
        }

        //verificação
        //Verifica se o objeto mockato executou um método sobre um parâmetro específíco
        // ususario <--> usuario2 [SUCESS/FAILDE]
        Mockito.verify(spc).possuiNegativacao(usuario);
    }

    /*
        Uma locacao esta atrasada e outra nao
     */

    @Test
    public void deveEnviarEmailParaLocacaoesAtrasadas(){
        //cenario
        Usuario usuario = umUsuario().agora();
        Usuario usuario2 = umUsuario().comNome("Usuario em dia").agora(); //nao vai ter a data atrasada
        Usuario usuario3 = umUsuario().comNome("Segundo usuario atrasado").agora();

        List<Locacao> listlocacaos = Arrays.asList(
                LocacaoBuilder.umLocacao().comUsuario(usuario).atrasada().agora(),
                LocacaoBuilder.umLocacao().comUsuario(usuario2).agora(),
                LocacaoBuilder.umLocacao().comUsuario(usuario3).atrasada().agora(),
                LocacaoBuilder.umLocacao().comUsuario(usuario3).atrasada().agora()
        );
        /*  ==> Nesse enário de teste, quando locacaoDAO for chamado, vai retornar essa lista
                    que acabamos de criar, em vez de nenhuma (já que o mock a implementa de forma default)
            ==> Apesar de essa chamada ser executada em outro metodo, eh necessario ter
                    locacaoDAO como variavel pra funcionar, por isso ela eh uma var global aqui
         */
        Mockito.when(locacaoDAO.obterLocacaoesPendentes()).thenReturn(listlocacaos);

        //ação
        service.notificarAtrasos();

        //verificação, se colcoar usuario2, vamos vê que vai falhar, pois, ele nao recebeu email
        // por que ele nao esta atrasadod

        /*  + Ele roda sobre usuario, por isso agnt verifica se rodou mesmo
            + mas, nao roda sobre usuario2 para isso fica diferetnes, agente coloca de forma que nunca rode nele
            +

         */

        Mockito.verify(emailService, Mockito.times(3)).notificarAtraso(Mockito.any(Usuario.class));
        Mockito.verify(emailService).notificarAtraso(usuario);
        // Quer dizer que espera ser executado ao menos duas vezes
        Mockito.verify(emailService, Mockito.atLeast(2)).notificarAtraso((usuario3));
        // Quer dizer que espera que seja executad exatamente duas vezes
        Mockito.verify(emailService, Mockito.times(2)).notificarAtraso(usuario3);
        // Nao deve ser enviado um email para o usuario 2
        Mockito.verify(emailService, Mockito.never()).notificarAtraso(usuario2);
        // Significa que nao vai ter mais nehuma inteaçao, depois desses tres
        Mockito.verifyNoMoreInteractions(emailService);
        // Quer dizer que espera que nao execute esse mock
        Mockito.verifyZeroInteractions(spc);
    }

    /*  5.8 - Capturando Exceções com o Mockito
        + Eu falo que o spc.possuinegativaçao, em vez de voltar true/false
            vou colocar que lança uma Exceptiob("Falha catastrofica")
        + Eu esepro que, depois dessa exception ele lance uma de LocadoraException
        + Tivemos que modificar a chmada do verificaNegativado no LocalService.class
            para que apareça as duas execoes (5.8)
     */
    @Test
    public void deveTratarErroSPC() throws Exception {
        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilme().agora());

        Mockito.when(spc.possuiNegativacao(usuario)).thenThrow(new Exception("Falha catastrofica"));

        //verificacao - forma Nova

        exception.expect(LocadoraException.class);
        exception.expectMessage("Problemas com SPC, tente novamente");

        //açao
        service.alugarFilme(usuario,filmes);

    }

    /*  5.9 - Capturando argumetnos
        + Aqui, temos um problema, agente manda uma locacoa, mas sera outra que vai
            executar o metodo, uma interna ao service, entao nao daria pra avaliar isso
            no test, pois o test nao sabe de nada de  dentro da funçao
        + Uma forma de fazer isso eh deixando o macther para Locacao.class, mas isso
            a torna muito genérica
        + Uma forma de fazer isso sra capturando o argumetno
     */
    @Test
    public void deveProrrogarLocacao(){
        //cenario
        Locacao locacao = LocacaoBuilder.umLocacao().agora();

        ArgumentCaptor<Locacao> argCapt = ArgumentCaptor.forClass(Locacao.class);

        //açao
        service.prorrogatLocacao(locacao, 3);

        // Primeira forma - SUCESS
        //Mockito.verify(locacaoDAO).salvar(Mockito.any(Locacao.class));

        //verificaçao
        /*
            Por que usalo?
                + dentro de verify, pra pegar esse pramentro quando for chamado:
                    Mockito.verify(locacaoDAO).salvar(argCapt.capture());
                + dentro do when, para pegar qualquer param que for lançado para o método:
                    Mockito.when(calc.soma(argCap.capture(), argCap.capture())).thenReturn(5);
         */
        // Indica que, quando detectar essa açâo sobre algum param, vai pegálo e colocar no argCap
        Mockito.verify(locacaoDAO).salvar(argCapt.capture());
        Locacao locacaoRetornado = argCapt.getValue();


        errorColetor.checkThat(locacaoRetornado.getValor(), is(12.0) );
        errorColetor.checkThat(locacaoRetornado.getDataLocacao(), ehHoje());
        errorColetor.checkThat(locacaoRetornado.getDataRetorno(), ehHojeComDiferencadeDias(3));
    }

    @Test
    @Ignore // funciona so com o powerMockito
    public void deveAlugarFilmeSemCalcularValor() throws Exception {
        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilme().agora());

        //PowerMock - metodo PRIVADO - com isso, significa que:
        //  Ao executar o metodo, vai retornar 1.0 e assim, nao vai executar o que ta dentro delee
        PowerMockito.doReturn(1.0).when(service, "calculoValorLocacao", filmes);

        //açao
        Locacao locacao = service.alugarFilme(usuario,filmes);

        //verificaçao - so consigo chegar em 1 se conseguir mockar o metodo privado
        Assert.assertThat(locacao.getValor(), is(1.0));

        // verificaçõa se um metodo privado foi chamado por uma power Mcok
        PowerMockito.verifyPrivate(service).invoke("calculoValorLocacao", filmes);
    }

    /*
        6.6 - power mock diretemente
            + Auqi estamos vendo se um metodo privado realmene faz aquilo que agente espera dele
            + Usamos uma WhroteBox para isso, ele invoraca esse método privado que na teoria dnao deveria ser capas
                de ser chamado
            + Ira retornar um valor, que usamos um Cast de DOuble pra guardar em uma variavel
            + E assim, entao testala no assert
     */
    @Test
    public void deveCalcularValorLocacao() throws Exception {
        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilme().agora());

        //açao
        //  POWER MOCK
        //Double valor = (Double) Whitebox.invokeMethod(service, "calculoValorLocacao", filmes);


        // 6.8 - Usando Reflexio
        Class<LocacaoService> clazz = LocacaoService.class;
        Method metodo = clazz.getDeclaredMethod("calculoValorLocacao", List.class);
        metodo.setAccessible(true);
        Double valor = (Double) metodo.invoke(service, filmes);


        //verificando
        Assert.assertThat(valor, is(4.0));

    }
}