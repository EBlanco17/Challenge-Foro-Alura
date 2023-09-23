package alura.foro.api.domain.autor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor implements UserDetails {
    @Id
    private UUID id;
    private String nombre;
    private String alias;
    private String email;
    private String clave;
    private LocalDateTime fechaRegistro;


    public Autor(DatosRegistroAutor datosRegistroAutor) {
        this.id = UUID.randomUUID();
        this.nombre = datosRegistroAutor.nombre();
        this.alias = datosRegistroAutor.alias();
        this.email = datosRegistroAutor.email();
        this.clave = new BCryptPasswordEncoder().encode(datosRegistroAutor.clave());
        this.fechaRegistro = LocalDateTime.now();
    }

    public void actualizarDatos(DatosActualizarAutor datosActualizarAutor) {
        if (datosActualizarAutor.nombre() != null) {
            this.nombre = datosActualizarAutor.nombre();
        }
        if (datosActualizarAutor.email() != null) {
            this.email = datosActualizarAutor.email();
        }
        if (datosActualizarAutor.clave() != null) {
            this.clave = datosActualizarAutor.clave();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return alias;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
