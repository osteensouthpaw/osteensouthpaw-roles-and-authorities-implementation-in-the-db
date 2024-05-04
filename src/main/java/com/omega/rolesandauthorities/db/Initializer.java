package com.omega.rolesandauthorities.db;

import com.omega.rolesandauthorities.role.Role;
import com.omega.rolesandauthorities.role.RoleRepository;
import com.omega.rolesandauthorities.users.User;
import com.omega.rolesandauthorities.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role roleUser = new Role();
        roleUser.setName("USER");
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEmail("john.doe@gmail.com");
        user.setRoles(Set.of(roleUser));
        userRepository.save(user);


        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Doe");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setEmail("admin@gmail.com");


        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setEmail("john.does@gmail.com");
        user1.setRoles(Set.of(roleUser, roleAdmin));
        roleUser.getUsers().add(user1);
        roleAdmin.getUsers().add(user1);

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(admin);
        roleAdmin.getUsers().add(admin);
        admin.setRoles(Set.of(roleAdmin));
        roleUser.getUsers().add(user);

        System.out.println(user);
        System.out.println(roleAdmin);

    }
}
