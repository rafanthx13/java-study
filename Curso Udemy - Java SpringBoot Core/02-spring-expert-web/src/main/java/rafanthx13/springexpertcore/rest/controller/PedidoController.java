package rafanthx13.springexpertcore.rest.controller;

import rafanthx13.springexpertcore.model.entity.ItemPedido;
import rafanthx13.springexpertcore.model.entity.Pedido;
import rafanthx13.springexpertcore.model.enums.StatusPedido;
import rafanthx13.springexpertcore.rest.dto.AtualizacaoStatusPedidoDTO;
import rafanthx13.springexpertcore.rest.dto.InformacaoItemPedidoDTO;
import rafanthx13.springexpertcore.rest.dto.InformacoesPedidoDTO;
import rafanthx13.springexpertcore.rest.dto.PedidoDTO;
import rafanthx13.springexpertcore.service.PedidoService;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }


    @GetMapping("{id}")
    public InformacoesPedidoDTO getById( @PathVariable Integer id ){
        return service.obterPedidoCompleto(id)
                .map( 
                    p -> converter(p) 
                )
                .orElseThrow(
                    () -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado.")
                );
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id , @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    // Converte o retorno para a class INformacoesPedidoDTO
    // Fara fazer isso usamos o @Builder do Lombok
    // É apenas para converter PEdido => InformacoesPedidoDTO
    private InformacoesPedidoDTO converter(Pedido pedido){

        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    // OverRidding ométodo anterior, para converter uma lista de Pedidos
    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            // É melhor retornar uma lsita vazia do que NUll
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}