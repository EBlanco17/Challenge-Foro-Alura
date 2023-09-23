package alura.foro.api.controller;

import alura.foro.api.domain.topico.*;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topicos", description = "Gestiona los topicos del foro")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RegistrarTopicoService registrarTopicoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo topico en la base de datos")
    public ResponseEntity<DatosDetalleTopico> crear(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        var response = registrarTopicoService.registrar(datosRegistroTopico);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un topico existente")
    public ResponseEntity<DatosDetalleTopico> actualizar(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosDetalleTopico(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(),  topico.getFechaCreacion(), topico.getCurso().getId(), topico.getEstado().toString()));
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de topicos")
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size =4, sort = "fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Desactiva un topico existente")
    public ResponseEntity eliminar(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un topico por su id")
    public ResponseEntity<DatosDetalleTopico> buscarPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(),  topico.getFechaCreacion(), topico.getCurso().getId(), topico.getEstado().toString()));
    }
}
