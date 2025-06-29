package com.viacodos.viacodos2.controller;

import com.viacodos.viacodos2.entity.Project;
import com.viacodos.viacodos2.service.ProjectService;
import com.viacodos.viacodos2.entity.Blog;
import com.viacodos.viacodos2.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "admin/dashboard";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "admin/projects";
    }

    @GetMapping("/projects/new")
    public String newProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "admin/project-form";
    }

    @PostMapping("/projects")
    public String createProject(@ModelAttribute Project project, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            String uploadDir = "src/main/resources/static/images/uploads/";
            try {
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, imageFile.getBytes());
                project.setImageUrl("/images/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        projectService.saveProject(project);
        return "redirect:/admin/projects";
    }

    @GetMapping("/projects/{id}/edit")
    public String editProjectForm(@PathVariable Long id, Model model) {
        projectService.getProjectById(id).ifPresent(project -> model.addAttribute("project", project));
        return "admin/project-form";
    }

    @PostMapping("/projects/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute Project project, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            String uploadDir = "src/main/resources/static/images/uploads/";
            try {
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, imageFile.getBytes());
                project.setImageUrl("/images/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        projectService.updateProject(id, project);
        return "redirect:/admin/projects";
    }

    @PostMapping("/projects/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/admin/projects";
    }

    // Blog Management
    @GetMapping("/blogs")
    public String blogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "admin/blogs";
    }

    @GetMapping("/blogs/new")
    public String newBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "admin/blog-form";
    }

    @PostMapping("/blogs")
    public String createBlog(@ModelAttribute Blog blog, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            String uploadDir = "src/main/resources/static/images/uploads/";
            try {
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, imageFile.getBytes());
                blog.setImageUrl("/images/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        blogService.saveBlog(blog);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/edit")
    public String editBlogForm(@PathVariable Long id, Model model) {
        blogService.getBlogById(id).ifPresent(blog -> model.addAttribute("blog", blog));
        return "admin/blog-form";
    }

    @PostMapping("/blogs/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            String uploadDir = "src/main/resources/static/images/uploads/";
            try {
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, imageFile.getBytes());
                blog.setImageUrl("/images/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        blogService.updateBlog(id, blog);
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }
} 