package ru.kata.restApp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.restApp.model.User;
import ru.kata.restApp.service.UsersService;
import ru.kata.restApp.util.UserNotFoundException;
import ru.kata.restApp.util.UsersErrorResponse;
import java.util.*;

@RestController
@RequestMapping("/admin/users")
public class AdminsRestController {

    private final UsersService usersService;

    @Autowired
    public AdminsRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ("id") long id) {
        User user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        usersService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        usersService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable ("id") long id) {
        usersService.removeUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UsersErrorResponse> handleUserNotFoundException (UserNotFoundException exception) {
        return new ResponseEntity<>(
                new UsersErrorResponse("User not found", System.currentTimeMillis()),
                HttpStatus.NOT_FOUND);
    }
}
