package rafanthx13.springexpertcore;

import rafanthx13.springexpertcore.model.entity.Cliente;
import rafanthx13.springexpertcore.model.entity.Pedido;
import rafanthx13.springexpertcore.model.repository.Clientes;
import rafanthx13.springexpertcore.model.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import java.util.List;

@SpringBootApplication
public class SpringExpertCoreApplication {

	@Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("Fulano");
            clientes.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            pedidos.findByCliente(fulano).forEach(System.out::println);



        };
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringExpertCoreApplication.class, args);
	}
}
