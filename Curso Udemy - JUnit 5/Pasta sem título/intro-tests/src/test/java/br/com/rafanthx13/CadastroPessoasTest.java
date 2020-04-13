package br.com.rafanthx13;

import org.junit.Test;

public class CadastroPessoasTest {
	
	@Test
	public void deveCriarOCadastroDePessoas(){
		CadastroPessoas cadastro = new CadastroPessoas();
		Assertions.assertThat(cadastro.getPessoas()).isEmpty();
	}

	@Test
	public void deveAdicionarUmaPessoa(){
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Wilson");

		cadastro.adicionar(pessoa);

		Assertions.assertThat(cadastro.getPessoas()).isNotEmpty().hasSize(1).contains(pessoa);
	}	

	@Test(expect = PessoaSemNomeException.class)
	public void naoDeveAdicionarPesosaSemNome(){
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoa pessoa = new Pessoa();

		cadastro.adicionar(pessoa);
	}

	@Test
	public void deveRemoverUmaPessoa(){
		// scenario
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Wilson");
		cadastro.adicionar(pessoa);
		// execution
		cadastro.remover(pessoa);
		// verification
		Assertions.assertThat(cadastro.getPessoas()).isEmpty();
	}

	@Test( expect = CadastroVazioException.class )
	public void deveLancarErrroAoTentarRemoverPessoaInexistente(){
		// scenario
		CadastroPessoas cadastro = new CadastroPessoas();
		Pessoa pessoa = new Pessoa();
		// execution
		cadastro.remover(pessoa);
	}
}