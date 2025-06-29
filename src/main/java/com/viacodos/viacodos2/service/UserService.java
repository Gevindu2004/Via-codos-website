package com.viacodos.viacodos2.service;

import com.viacodos.viacodos2.entity.ERole;
import com.viacodos.viacodos2.entity.Role;
import com.viacodos.viacodos2.entity.User;
import com.viacodos.viacodos2.repository.RoleRepository;
import com.viacodos.viacodos2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createAdminUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already in use!");
        }

        User user = new User(username, email, passwordEncoder.encode(password));

        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public boolean adminUserExists() {
        return userRepository.findByUsername("admin_viacodos").isPresent();
    }

    public void createAdminUserIfNotExists() {
        if (!adminUserExists()) {
            try {
                createAdminUser("admin_viacodos", "admin@viacodos.com", "viacodos123");
                System.out.println("Admin user created successfully!");
            } catch (Exception e) {
                System.out.println("Error creating admin user: " + e.getMessage());
            }
        } else {
            System.out.println("Admin user already exists!");
        }
    }

    public void initializeRoles() {
        if (roleRepository.count() == 0) {
            Role userRole = new Role(ERole.ROLE_USER);
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            roleRepository.save(userRole);
            roleRepository.save(adminRole);
        }
    }
} 