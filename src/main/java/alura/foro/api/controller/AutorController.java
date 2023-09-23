package alura.foro.api.controller;

import alura.foro.api.domain.autor.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/autores")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Autor", description = "Gestiona los autores del foro")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Operation(summary = "Lista todos los autores")
    @GetMapping
    public ResponseEntity<Page<DatosListadoAutor>> listar (@PageableDefault(size =4, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(autorRepository.findAll(paginacion).map(DatosListadoAutor::new));
    }


    @Operation(summary = "Actualiza los datos de un autor existente")
    @Transactional
    @PutMapping
    public ResponseEntity<DatosRespuestaAutor> actualizar(@RequestBody @Valid DatosActualizarAutor datosActualizarAutor) {
        System.out.println(datosActualizarAutor);
        Autor autor = autorRepository.getReferenceById(datosActualizarAutor.id());
        autor.actualizarDatos(datosActualizarAutor);
        return ResponseEntity.ok(new DatosRespuestaAutor(autor.getId(), autor.getNombre(), autor.getAlias() ,autor.getEmail(), autor.getFechaRegistro()));
    }

    @Operation(summary = "Muestra un autor por su id")
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaAutor> buscarPorId(@PathVariable UUID id) {
        Autor autor = autorRepository.getReferenceById(id);
        var datosRespuestaAutor = new DatosRespuestaAutor(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail(), autor.getFechaRegistro());
        return ResponseEntity.ok(datosRespuestaAutor);
    }


}
