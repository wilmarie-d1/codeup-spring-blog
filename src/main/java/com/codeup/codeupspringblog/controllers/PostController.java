package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
    public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String showPosts(Model model){

        model.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.findById(id).get());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String postCreateForm(){
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String postCreateSubmit(@RequestParam String title, @RequestParam String body){

        Post newPost = new Post(title, body, (User) userDao.findById(1L).get());

        postDao.save(newPost);

        return "redirect:/posts";
    }

    }