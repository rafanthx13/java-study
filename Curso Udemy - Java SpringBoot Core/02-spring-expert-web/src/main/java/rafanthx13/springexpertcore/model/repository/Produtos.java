package rafanthx13.springexpertcore.model.repository;

import rafanthx13.springexpertcore.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
