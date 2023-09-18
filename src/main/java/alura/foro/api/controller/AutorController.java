package alura.foro.api.controller;

import alura.foro.api.domain.autor.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Operation(summary = "Lista todos los autores")
    @GetMapping
    public String listar(@PageableDefault(size =4, sort = "nombre") Pageable paginacion) {
        return autorRepository.findAll(paginacion).toString();
    }


    @Operation(summary = "Registra un nuevo autor en la base de datos")
    @Transactional
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosRegistroAutor datosRegistroAutor, UriComponentsBuilder uriComponentsBuilder) {
        Autor autor = autorRepository.save(new Autor(datosRegistroAutor));
        DatosRespuestaAutor datosRespuestaAutor = new DatosRespuestaAutor(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail());
        URI location = uriComponentsBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).body(datosRespuestaAutor);
    }

    @Operation(summary = "Actualiza los datos de un autor existente")
    @Transactional
    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody @Valid DatosActualizarAutor datosActualizarAutor) {
        Autor autor = autorRepository.getReferenceById(datosActualizarAutor.id());
        autor.actualizarDatos(datosActualizarAutor);

        return ResponseEntity.ok(new DatosRespuestaAutor(autor.getId(), autor.getNombre(), autor.getAlias() ,autor.getEmail()));
    }

    @Operation(summary = "Muestra un autor por su id")
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable UUID id) {
        Autor autor = autorRepository.getReferenceById(id);
        var datosRespuestaAutor = new DatosRespuestaAutor(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail());
        return ResponseEntity.ok(datosRespuestaAutor);
    }


}
