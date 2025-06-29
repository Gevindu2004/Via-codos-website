package com.viacodos.viacodos2;

import com.viacodos.viacodos2.service.ProjectService;
import com.viacodos.viacodos2.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String home() {
        logger.info("Accessing public home page");
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Accessing test page");
        return "test";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        logger.info("Accessing public dashboard page");
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "dashboard";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        logger.info("Accessing about-us page");
        return "about-us";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        logger.info("Accessing projects page");
        model.addAttribute("projects", projectService.getAllProjects());
        return "Projects";
    }

    @GetMapping("/contact")
    public String contact() {
        logger.info("Accessing contact page");
        return "Contact";
    }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        logger.info("Accessing blogs and news page");
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blogs";
    }
} 