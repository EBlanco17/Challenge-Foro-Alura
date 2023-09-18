package alura.foro.api.domain.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DatosRegistroAutor(
        @NotBlank
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,

        @NotBlank
        @Size(min = 6, max = 15, message = "El alias debe tener entre 6 y 15 caracteres")
        String alias,
        @NotBlank
        @Email(message = "El email debe tener un formato válido")
        String email,
        @NotBlank
        @Size(min = 6, max = 14, message = "La clave debe tener entre 6 y 14 caracteres")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,14}$", message = "La clave debe tener al menos una letra mayúscula, una minúscula, un número y un caracter especial")
        String clave) {
}
