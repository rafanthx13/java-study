package com.cursodsousa.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class MeuResource {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente ){
        //service.save(cliente);
        return cliente;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Long id ){
        //service.buscarPorId(id);
        //service.delete(cliente);
    }

    @PutMapping("{id}")
    public Cliente atualizar( @PathVariable Long id, @RequestBody Cliente cliente ){
        //service.buscaPorId(id);
        //service.update(cliente);
        return cliente;
    }

    @GetMapping("{id}")
    public Cliente obterDadosCliente( @PathVariable Long id ){
        System.out.println(String.format("Id recebido via url: %d", id));
        Cliente cliente = new Cliente("Fulano", "000.000.000-00");
        return cliente;
    }
}
