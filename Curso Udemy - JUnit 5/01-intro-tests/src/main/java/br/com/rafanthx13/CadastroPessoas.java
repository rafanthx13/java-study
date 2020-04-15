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