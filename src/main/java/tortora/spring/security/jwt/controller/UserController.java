package tortora.spring.security.jwt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tortora.spring.security.jwt.model.User;
import tortora.spring.security.jwt.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public void postUser(@RequestBody User user){
        service.createUser(user);
    }

}
