package br.com.rafanthx13.FirstSpringProject.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafanthx13.FirstSpringProject.model.Endereco;

@RestController
public class EnderecoController {
  
  private	List<Endereco> enderecos = new ArrayList<>(Arrays.asList(
			new Endereco("38408100", "Joao Naves de Avila", "Uberlandia"),
			new Endereco("38970000", "Getulio Portela", "Campos Altos")
	));
	
	@GetMapping("/address")
	public List<Endereco> getAllAddress(){
		return enderecos;
	}
}