package alura.foro.api.controller;

import alura.foro.api.domain.curso.*;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cursos", description = "Gestiona los cursos del foro")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo curso en la base de datos")
    public ResponseEntity<DatosRespuestaCurso> crear(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso) {
        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getEstado());
        return ResponseEntity.ok(datosRespuestaCurso);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un curso existente")
    public ResponseEntity<DatosRespuestaCurso> actualizar(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);
        return ResponseEntity.ok(new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getEstado()));
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de cursos")
    public ResponseEntity<Page<DatosListadoCurso>> listar(@PageableDefault(size =4, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(DatosListadoCurso::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Desactiva un curso existente")
    public ResponseEntity eliminar(@PathVariable Integer id) {
        Curso curso = cursoRepository.getReferenceById(id);
        curso.desactivarCurso();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un curso por su id")
    public ResponseEntity<DatosRespuestaCurso> buscarPorId(@PathVariable Integer id) {
        Curso curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getEstado()));
    }
}
