package rafanthx13.springexpertcore.rest.controller;

import rafanthx13.springexpertcore.model.entity.Cliente;
import rafanthx13.springexpertcore.model.repository.Clientes;

import org.springframework.data.model.Example;
import org.springframework.data.model.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
public class ClienteController {

    private Clientes clientesRepository;

    // ja vai auto-injetar
    public ClienteController( Clientes clientes ) {
        this.clientesRepository = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById( @PathVariable Integer id ){
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.ok( cliente.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save( @RequestBody Cliente cliente ){
        Cliente clienteSalvo = clientesRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity delete( @PathVariable Integer id ){
	  Optional<Cliente> cliente = clientesRepository.findById(id);

	  if(cliente.isPresent()){
	      clientesRepository.delete( cliente.get() );
	      return ResponseEntity.noContent().build();
	  }

	  return ResponseEntity.notFound().build();
	}

	@PutMapping("/api/clientes/{id}")
	@ResponseBody
	
	public ResponseEntity update( @PathVariable Integer id, @RequestBody Cliente cliente ){
	    return clientesRepository
	            .findById(id)
	            .map( clienteExistente -> {
	                cliente.setId(clienteExistente.getId());
	                clientesRepository.save(cliente);
	                return ResponseEntity.noContent().build();
	            }).orElseGet( () -> ResponseEntity.notFound().build() );
	}

	@GetMapping("/api/clientes")
    public ResponseEntity find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
        	.withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);
    }

}
