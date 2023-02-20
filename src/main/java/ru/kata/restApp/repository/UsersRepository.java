package ru.kata.restApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.restApp.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}
