# JUnit4



## Usos



### 01

```java
package br.com.rafanthx13;

import java.util.ArrayList;
import java.util.List;

import br.com.rafanthx13.errors.CadastroVazioException;
import br.com.rafanthx13.errors.PessoaSemNomeException;

public class CadastroPessoas {

	private List<Pessoas> pessoas;

	public CadastroPessoas(){
		this.pessoas = new ArrayList<>();
	}

	public List<Pessoas> getPessoas(){
		return this.pessoas;
	}

	public void adicionar(Pessoas pessoa){
		if(pessoa.getNome() == null){
			throw new PessoaSemNomeException();
		}
		this.pessoas.add(pessoa);
	}

	public void remover(Pessoas pessoa){
		if(this.pessoas.isEmpty()){
			throw new CadastroVazioException();
		}
		this.pessoas.remove(pessoa);
	}
}


```

```java
package br.com.rafanthx13;

/**
 * Hello world!
 *
 */
public class Pessoas {

	private String nome;

	public String getNome(){
		return this.nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
    
}
```



```java
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
```

