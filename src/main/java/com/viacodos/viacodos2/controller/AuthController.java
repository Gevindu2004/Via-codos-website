package com.viacodos.viacodos2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
        }
        
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been successfully logged out.");
        }
        
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               RedirectAttributes redirectAttributes) {
        
        // Basic validation
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
            return "redirect:/signup";
        }
        
        if (password.length() < 6) {
            redirectAttributes.addFlashAttribute("error", "Password must be at least 6 characters long!");
            return "redirect:/signup";
        }
        
        // TODO: Add user registration logic here
        // For now, just redirect with a success message
        redirectAttributes.addFlashAttribute("success", "Account created successfully! Please sign in.");
        return "redirect:/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
} 