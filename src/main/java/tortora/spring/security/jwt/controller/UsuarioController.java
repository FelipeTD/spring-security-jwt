package tortora.spring.security.jwt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tortora.spring.security.jwt.model.Usuario;
import tortora.spring.security.jwt.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public void postUser(@RequestBody Usuario usuario) {
        service.createUsuario(usuario);
    }

}
