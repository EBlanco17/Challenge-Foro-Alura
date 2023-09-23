package alura.foro.api.domain.topico;

import alura.foro.api.domain.autor.AutorRepository;
import alura.foro.api.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarTopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AutorRepository autorRepository;

    public DatosDetalleTopico registrar(DatosRegistroTopico datos) {
        if(!cursoRepository.existsById(datos.cursoId())) {
            throw new IllegalArgumentException("El curso no existe");
        }
        if (!autorRepository.existsById(datos.autorId())) {
            throw new IllegalArgumentException("El autor no existe");
        }
        var curso = cursoRepository.findById(datos.cursoId()).get();
        var autor = autorRepository.findById(datos.autorId()).get();

        var topico = new Topico(datos, autor, curso);

        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);

    }
}
