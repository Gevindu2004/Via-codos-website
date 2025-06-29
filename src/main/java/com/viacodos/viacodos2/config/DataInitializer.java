package com.viacodos.viacodos2.config;

import com.viacodos.viacodos2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Starting Data Initialization ===");
        
        // Initialize roles
        System.out.println("Initializing roles...");
        userService.initializeRoles();
        System.out.println("Roles initialized successfully!");
        
        // Create admin user if it doesn't exist
        System.out.println("Checking for admin user...");
        userService.createAdminUserIfNotExists();
        
        System.out.println("=== Data Initialization Complete ===");
        System.out.println("Admin credentials: username=admin, password=admin123");
    }
} 