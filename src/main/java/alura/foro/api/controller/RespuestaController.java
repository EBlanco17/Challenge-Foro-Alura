package alura.foro.api.controller;

import alura.foro.api.domain.respuesta.DatosActualizarRespuesta;
import alura.foro.api.domain.respuesta.DatosRegistroRespuesta;
import alura.foro.api.domain.respuesta.Respuesta;
import alura.foro.api.domain.respuesta.RespuestaRepository;
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

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuestas", description = "Gestiona las respuestas del foro")
public class RespuestaController {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra una nueva respuesta a un topico")
    public ResponseEntity<?> crear(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta) {
        respuestaRepository.save(new Respuesta());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza una respuesta existente")
    public ResponseEntity<?> actualizar(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<Page<?>> listar(@PageableDefault(size =4, sort = "fechaCreacion") Pageable paginacion, @PathVariable Long idTopico) {
        //respuestaRepository.findByTopicoId(idTopico, paginacion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina una respuesta existente")
    public ResponseEntity eliminar(@PathVariable Long id) {
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
