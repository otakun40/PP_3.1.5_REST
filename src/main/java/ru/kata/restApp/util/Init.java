package ru.kata.restApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.restApp.model.Role;
import ru.kata.restApp.model.User;
import ru.kata.restApp.service.RolesService;
import ru.kata.restApp.service.UsersService;
import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class Init {

    private final RolesService roleService;
    private final UsersService usersService;

    @Autowired
    public Init(RolesService roleService, UsersService usersService) {
        this.roleService = roleService;
        this.usersService = usersService;
    }

    @PostConstruct
    public void initialization() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        User admin = new User("Anton", "Ivanov", 18, "admin@gmail.com", "admin",
                Set.of(roleAdmin, roleUser));
        usersService.saveUser(admin);

        roleService.saveRole(roleUser);
        User user = new User("Ivan", "Antonov", 22, "user@gmail.com", "user",
                Set.of(roleUser));
        usersService.saveUser(user);

    }
}
