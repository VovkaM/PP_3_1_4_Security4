package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Init {
    private final RoleRepository roleRepository;
    private final UserService userService;
    @Autowired
    public Init(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void initTestUsers() {
        Role userTest = new Role(1L, "ROLE_ADMIN", "Admin");
        Role adminTest = new Role(2L, "ROLE_USER", "User");
        roleRepository.save(userTest);
        roleRepository.save(adminTest);
        Set<Role> userTestRole = Stream.of(userTest).collect(Collectors.toSet());
        Set<Role> adminTestRole = Stream.of(adminTest).collect(Collectors.toSet());
        User user = new User("admin", "admin", 53,
                "admin", "admin", userTestRole);
        User admin = new User("user", "user", 58,
                "user", "user", adminTestRole);
        userService.save(user);
        userService.save(admin);
    }
}
