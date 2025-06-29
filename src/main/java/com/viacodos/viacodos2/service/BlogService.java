package com.viacodos.viacodos2.service;

import com.viacodos.viacodos2.entity.Blog;
import com.viacodos.viacodos2.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public Blog updateBlog(Long id, Blog blogDetails) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        blog.setTitle(blogDetails.getTitle());
        blog.setContent(blogDetails.getContent());
        blog.setAuthor(blogDetails.getAuthor());
        blog.setImageUrl(blogDetails.getImageUrl());

        return blogRepository.save(blog);
    }
} 