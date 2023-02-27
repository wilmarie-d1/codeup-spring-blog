

package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.iconspringblog.models.Post;
import com.codeup.iconspringblog.repositories.PostRepository;
import com.codeup.iconspringblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDaoService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDaoService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public void savePost(Post post) {
        post.setUser(userRepository.findById(1L).get());
        postRepository.save(post);
    }

    // READ
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(long id) {
        if (postRepository.findById(id).isPresent()) {
            return postRepository.findById(id).get();
        } else {
            throw new RuntimeException("Post was not found.");
        }
    }

    // DELETE
    public void deletePostById(long id) {
        postRepository.deleteById(id);
    }

}