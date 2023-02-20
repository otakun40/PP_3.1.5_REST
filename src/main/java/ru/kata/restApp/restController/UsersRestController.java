package ru.kata.restApp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.restApp.model.User;
import ru.kata.restApp.service.UsersService;
import java.security.Principal;



@Controller
@RequestMapping("/user")
public class UsersRestController {

    private final UsersService usersService;

    @Autowired
    public UsersRestController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/current")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = usersService.getUserByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }
}
