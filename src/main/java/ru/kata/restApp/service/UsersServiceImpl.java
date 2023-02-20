package ru.kata.restApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.restApp.model.Role;
import ru.kata.restApp.repository.UsersRepository;
import ru.kata.restApp.model.User;
import ru.kata.restApp.util.UserNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> userById = usersRepository.findById(id);
        if (userById.isPresent()) {
            return userById.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (user.getPassword().equals("")) {
            user.setPassword(usersRepository.getUserByEmail(user.getEmail()).getPassword());
        }
        else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        usersRepository.save(user);
    }

    @Transactional
    public void removeUserById(Long id) {
        Optional<User> userById = usersRepository.findById(id);
        if (userById.isPresent()) {
            usersRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User user = getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
