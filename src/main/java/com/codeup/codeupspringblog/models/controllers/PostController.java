package com.codeup.codeupspringblog.models.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.PostDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class PostController{

        private final PostDaoService postService;
        private final EmailService emailService;

        public PostController(PostDaoService postService, EmailService emailService) {
            this.postService = postService;
            this.emailService = emailService;
        }

        @GetMapping("/posts")
        public String showPosts(Model model){
            model.addAttribute("allPosts", postService.getAllPosts());
            return "posts/index";
        }

        @GetMapping("/posts/{id}")
        public String singlePost(@PathVariable long id, Model model){
            model.addAttribute("post", postService.getPostById(id));
            return "posts/show";
        }

        @GetMapping("/posts/create")
        public String postCreateForm(Model model){
            model.addAttribute("post", new Post());
            return "posts/create";
        }

        @PostMapping(path = "/posts/create")
        public String postCreateSubmit(@ModelAttribute Post post){
            postService.savePost(post);
            emailService.sendTextEmail(post);
            return "redirect:/posts";
        }

        @GetMapping("/posts/{id}/edit")
        public String showEditForm(@PathVariable long id, Model model) {
            Post postToEdit = postService.getPostById(id);
            model.addAttribute("post", postToEdit);
            return "posts/edit";
        }

        @PostMapping("/posts/{id}/edit")
        public String submitPostChanges(@PathVariable long id, @ModelAttribute Post post) {
            postService.savePost(post);
            return "redirect:/posts/" + id;
        }


}