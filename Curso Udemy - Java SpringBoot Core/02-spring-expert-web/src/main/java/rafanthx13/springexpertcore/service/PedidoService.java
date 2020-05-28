package rafanthx13.springexpertcore.service;

import rafanthx13.springexpertcore.model.entity.Pedido;
import rafanthx13.springexpertcore.rest.dto.PedidoDTO;

public interface PedidoService {
	
    Pedido salvar( PedidoDTO dto );

    Optional<Pedido> obterPedidoCompleto(Integer id);
    
    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
