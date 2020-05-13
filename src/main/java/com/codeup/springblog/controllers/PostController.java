package com.codeup.springblog.controllers;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency injection
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


    //    Posts index page

    @GetMapping("/posts")
    public String returnAllPosts(Model model) {

        // WITH JPA:
        model.addAttribute("posts", postDao.findAll()); // Get all the posts with the built in JPA functionality!


//        // BEFORE JPA (hardcoded):
//        // Create an ArrayList to store all the posts
//        List<Post> postList = new ArrayList<>();
//
//        // Add two new posts to the list
//        postList.add(new Post("Post 1", "Post 1 body"));
//        postList.add(new Post("Post 2", "Post 2 body"));
//
//        // Add the ArrayList to the model as an attribute ("posts" is the name that is referenced in the view)
//        model.addAttribute("posts", postList);


        // Return the view
        return "/posts/index";
    }




//    View an individual post

    @GetMapping(path = "/posts/{id}")
    // Pass id, title, and body variables to the controller
    public String returnPostById(@PathVariable long id, Model model) {

        // With JPA:
        model.addAttribute("post", postDao.getPostById(id));


        // Before JPA:
//        // Create a dummy post
//        Post dummyData = new Post(id, "Dummy Post", "lorem ipsum dolor");
//
//        // Add attribute to the model
//        model.addAttribute("post", dummyData);

        // Return the view
        return "/posts/show";
    }




//    View the form for creating a post

    @GetMapping(path = "/posts/create")
    public String viewCreatePostForm() {
        return "/posts/create";
    }




//    Create a new post

    @PostMapping(path = "/posts/create")
    public String createPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
        Post post = new Post(title, body);
        postDao.save(post);
        model.addAttribute("post", post);

        return "redirect:/posts/index";
    }




//    Edit a post

    @GetMapping("/posts/{id}/edit")
    public String postEditForm(@PathVariable long id, Model model) {
        Post post = postDao.getPostById(id);
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {

        Post post = postDao.getPostById(id);
        post.setTitle(title);
        post.setBody(body);

        postDao.save(post);

        return "redirect:/posts";
    }


//    Delete a post

    @PostMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id) {
        postDao.deleteById(id);

        return "redirect:/posts";
    }
}