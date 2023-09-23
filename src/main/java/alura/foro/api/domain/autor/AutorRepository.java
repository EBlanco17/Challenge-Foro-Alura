package alura.foro.api.domain.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    UserDetails findByAlias(String alias);
}
