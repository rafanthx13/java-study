package br.ce.wcaquino.servicos;

import br.ce.wcaquino.dao.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeInexistenteException;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

//import buildermaster.BuilderMaster;

public class LocacaoService {

    private LocacaoDAO locacaoDAO;
    private SPCService spcService;
    private EmailService emailService;

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeInexistenteException, FilmeSemEstoqueException, LocadoraException {

        if (filmes == null || filmes.isEmpty()) {
            throw new FilmeInexistenteException("Filme sem Estoque!");
        }

        for (Filme eachFilm : filmes) {
            if (eachFilm.getEstoque() == 0.00) {
                throw new FilmeSemEstoqueException("Filme sem Estoque!");
            }
        }


        boolean negativado;
        try {
            negativado = spcService.possuiNegativacao(usuario);
        } catch (Exception e) {
            throw new LocadoraException("Problemas com SPC, tente novamente");
        }

        if(negativado) {
            throw new LocadoraException("Usuario Negativado");
        }

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(obterData());

        /*
		for(Filme eachFilm: filmes){
			acumulado += eachFilm.getPrecoLocacao();
		}
		*/
        locacao.setValor(calculoValorLocacao(filmes));

        //Entrega no dia seguinte
        Date dataEntrega = obterData();
        dataEntrega = adicionarDias(dataEntrega, 1);
        if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
            dataEntrega = adicionarDias(dataEntrega, 1);
        }
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        locacaoDAO.salvar(locacao);

        return locacao;
    }

    protected Date obterData() {
        return new Date();
    }

    private double calculoValorLocacao(List<Filme> filmes) {
        System.out.println("Estou calculdando");
        double acumulado = 0.0;
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            Double valorFilme = filme.getPrecoLocacao();
            switch (i) {
                case 2:
                    valorFilme = valorFilme * 0.75;
                    break;
                case 3:
                    valorFilme = valorFilme * 0.50;
                    break;
                case 4:
                    valorFilme = valorFilme * 0.25;
                    break;
                case 5:
                    valorFilme = 0.0;
                    break;
            }
            acumulado += valorFilme;
        }
        return acumulado;
    }

    /*		Graças ao Mock que criamos no metodo: deveEnviarEmailParaLocacaoesAtrasadas()
                Quando estivermos testando, quando chegar nessa parte,
                locacaoes vai receber a lista que declaramos no método
                Assim, estamos implemnetando esse obterLocacaoesPendentes
    */
    public void notificarAtrasos() {
        List<Locacao> locacoes = locacaoDAO.obterLocacaoesPendentes();
        for (Locacao locacao : locacoes) {
            if (locacao.getDataRetorno().before(obterData())) {
                emailService.notificarAtraso(locacao.getUsuario());
            }
        }
    }

    public void prorrogatLocacao(Locacao locacao, int dias){
        Locacao novaLocacao = new Locacao();
        novaLocacao.setUsuario(locacao.getUsuario());
        novaLocacao.setFilmes(locacao.getFilmes());
        novaLocacao.setDataLocacao(locacao.getDataLocacao());
        novaLocacao.setValor(locacao.getValor()*dias);
        novaLocacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(dias));
        locacaoDAO.salvar(novaLocacao);
    }


    public void setLocacaoDAO(LocacaoDAO locacaoDAO) {
        this.locacaoDAO = locacaoDAO;
    }

    public void setSPCService(SPCService spc) {
        spcService = spc;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}