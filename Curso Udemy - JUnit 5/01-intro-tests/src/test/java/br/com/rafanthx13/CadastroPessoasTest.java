package br.com.rafanthx13;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import br.com.rafanthx13.errors.CadastroVazioException;
import br.com.rafanthx13.errors.PessoaSemNomeException;

public class CadastroPessoasTest {
	
	@Test
	public void deveCriarOCadastroDePessoas(){
		CadastroPessoas cadastro = new CadastroPessoas();
		Assertions.assertThat(cadastro.getPessoas()).isEmpty();
	}

	@Test
	public void deveAdicionarUmaPessoa(){
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoas pessoa = new Pessoas();
		pessoa.setNome("Wilson");

		cadastro.adicionar(pessoa);

		Assertions.assertThat(cadastro.getPessoas()).isNotEmpty().hasSize(1).contains(pessoa);
	}	

	@Test(expected  = PessoaSemNomeException.class)
	public void naoDeveAdicionarPesosaSemNome(){
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoas pessoa = new Pessoas();

		cadastro.adicionar(pessoa);
	}

	@Test
	public void deveRemoverUmaPessoa(){
		// scenario
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoas pessoa = new Pessoas();
		pessoa.setNome("Wilson");
		cadastro.adicionar(pessoa);
		// execution
		cadastro.remover(pessoa);
		// verification
		Assertions.assertThat(cadastro.getPessoas()).isEmpty();
	}

	@Test( expected  = CadastroVazioException.class )
	public void deveLancarErrroAoTentarRemoverPessoaInexistente(){
		// scenario
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoas pessoa = new Pessoas();
		// execution
		cadastro.remover(pessoa);
	}
}