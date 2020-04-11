package curso.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.api.rest.model.Usuario;

@Repository // Injeção de dependência
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{// Utilizo a classe que vou persistir e o tipo da variavel que representa o objeto

}
