package rafanthx13.springexpertcore.service.impl;

import rafanthx13.springexpertcore.model.entity.Cliente;
import rafanthx13.springexpertcore.model.entity.ItemPedido;
import rafanthx13.springexpertcore.model.entity.Pedido;
import rafanthx13.springexpertcore.model.entity.Produto;
import rafanthx13.springexpertcore.model.repository.Clientes;
import rafanthx13.springexpertcore.model.repository.ItemsPedido;
import rafanthx13.springexpertcore.model.repository.Pedidos;
import rafanthx13.springexpertcore.model.repository.Produtos;
import rafanthx13.springexpertcore.exception.RegraNegocioException;
import rafanthx13.springexpertcore.rest.dto.ItemPedidoDTO;
import rafanthx13.springexpertcore.rest.dto.PedidoDTO;
import rafanthx13.springexpertcore.service.PedidoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // LOMBOK : Serve para já gerar um construtor para as variaveis que forem private e final
public class PedidoServiceImpl implements PedidoService {

    // Os repositories sao injetados pelo Lombok
    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    /*
    PedidoDTO
    {
        cliente: 1,
        total: 100,
        items: [
            {
                produto: 1,
                quantidade: 10
            }
        ]
    }
    */

    @Override
    @Transactional // necessária pois várias operaçôes no banco de dados para virar ATOMICA
    public Pedido salvar( PedidoDTO dto ) {
        // Primeiro buscamos o client desse pedido pelo ID do DTO
        Integer idCliente = dto.getCliente();
        // se nao achar um cliente, manda uma exception
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
        // Encontrando um cliente vamos gera o pedido
        Pedido pedido = new Pedido(); // Criamos o pedido
        // setamos alguns dados
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        // Agora eu tenho que salavar os itens do pedido
        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        // verifica se há pedidos
        if(items.isEmpty()){
            throw new RegraNegocioException(
                "Não é possível realizar um pedido sem items."
            );
        }
        // procesamento de itens: converto ItemPedido de DTO para Entity
        return items.stream().map( dto -> {
                // Busco se há mesmo o produto cadastrado
                Integer idProduto = dto.getProduto();
                Produto produto = produtosRepository
                    .findById(idProduto)
                    .orElseThrow( () -> new RegraNegocioException(
                        "Código de produto inválido: "+ idProduto
                    ));
                // havendo, converto para entity
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setQuantidade(dto.getQuantidade());
                itemPedido.setPedido(pedido);
                itemPedido.setProduto(produto);
                return itemPedido;
            }).collect(Collectors.toList()); // converto em lista

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus( Integer id, StatusPedido statusPedido ) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }

}
