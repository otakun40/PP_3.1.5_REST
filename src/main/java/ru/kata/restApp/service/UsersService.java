package ru.kata.restApp.service;

import ru.kata.restApp.model.User;
import java.util.List;

public interface UsersService {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void updateUser(User user);


    void removeUserById(Long id);
}
