package tortora.spring.security.jwt.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tortora.spring.security.jwt.dtos.Login;
import tortora.spring.security.jwt.dtos.Sessao;
import tortora.spring.security.jwt.model.User;
import tortora.spring.security.jwt.repository.UserRepository;
import tortora.spring.security.jwt.security.JWTCreator;
import tortora.spring.security.jwt.security.JWTObject;
import tortora.spring.security.jwt.security.SecurityConfig;

import java.util.Date;

@RestController
public class LoginController {

    private PasswordEncoder encoder;

    private SecurityConfig securityConfig;

    private UserRepository repository;

    public LoginController(PasswordEncoder encoder, SecurityConfig securityConfig, UserRepository repository) {
        this.encoder = encoder;
        this.securityConfig = securityConfig;
        this.repository = repository;
    }

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        User user = repository.findByUsername(login.getUsername());

        if(user != null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getPassword());

            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }

            // Enviando um objeto Sessão para retornar mais informações do utilizador
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));

            return sessao;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
