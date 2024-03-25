package tortora.spring.security.jwt.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tortora.spring.security.jwt.model.Usuario;
import tortora.spring.security.jwt.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final PasswordEncoder enconder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder enconder) {
        this.repository = repository;
        this.enconder = enconder;
    }

    public void createUsuario(Usuario usuario) {
        String pass = usuario.getPassword();
        usuario.setPassword(enconder.encode(pass));
        repository.save(usuario);
    }


}
