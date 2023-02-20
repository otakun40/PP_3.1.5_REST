package ru.kata.restApp.service;

import ru.kata.restApp.model.Role;
import java.util.List;

public interface RolesService {

    List<Role> getRoles();

    void saveRole(Role roleAdmin);

    void removeRoleById(Long id);

    Role getRoleByName(String name);
}
