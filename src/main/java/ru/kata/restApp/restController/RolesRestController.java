package ru.kata.restApp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.restApp.model.Role;
import ru.kata.restApp.service.RolesService;
import java.util.List;

@RestController
@RequestMapping("/admin/roles")
public class RolesRestController {
    private final RolesService service;

    @Autowired
    public RolesRestController(RolesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(service.getRoles());
    }
}
