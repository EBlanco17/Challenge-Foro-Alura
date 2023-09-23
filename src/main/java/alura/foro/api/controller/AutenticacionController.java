package alura.foro.api.controller;

import alura.foro.api.domain.autor.*;
import alura.foro.api.infra.security.TokenService;
import alura.foro.api.infra.security.DatosJWTtoken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController

@Tag(name = "Autenticacion", description = "Login y registro de usuarios, da acceso al resto de endpoint")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping("/login")
    @Operation(summary = "Autentica un usuario y devuelve un token JWT")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario credenciales) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(credenciales.alias(), credenciales.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((Autor) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

    @PostMapping("/registro")
    @Operation(summary = "Registra un nuevo autor en la base de datos")
    @Transactional
    public ResponseEntity<DatosRespuestaAutor> registrar(@RequestBody @Valid DatosRegistroAutor datosRegistroAutor, UriComponentsBuilder uriComponentsBuilder) {
        Autor autor = autorRepository.save(new Autor(datosRegistroAutor));
        DatosRespuestaAutor datosRespuestaAutor = new DatosRespuestaAutor(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail(), autor.getFechaRegistro());
        URI location = uriComponentsBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).body(datosRespuestaAutor);
    }
}
