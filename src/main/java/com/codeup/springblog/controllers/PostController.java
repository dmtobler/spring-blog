package com.codeup.springblog.controllers;

import com.codeup.springblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//    Posts index page

    @GetMapping("/posts")
    public String returnAllPosts(Model model) {

        // Create an ArrayList to store all the posts
        List<Post> postList = new ArrayList<>();

        // Add two new posts to the list
        postList.add(new Post("Post 1", "Post 1 body"));
        postList.add(new Post("Post 2", "Post 2 body"));

        // Add the ArrayList to the model as an attribute ("posts" is the name that is referenced in the view)
        model.addAttribute("posts", postList);

        // Return the view
        return "/posts/index";
    }



//    View an individual post

    @GetMapping(path = "/posts/{id}")
    // Pass id, title, and body variables to the controller
    public String returnPostById(@PathVariable long id, Model model) {

        // Create a dummy post
        Post dummyData = new Post(id, "Dummy Post", "lorem ipsum dolor");

        // Add attribute to the model
        model.addAttribute("post", dummyData);

        // Return the view
        return "/posts/show";
    }

//    View the form for creating a post

    @GetMapping(path = "/posts/create")
    @ResponseBody
    public String viewCreatePostForm() {
        return "Viewing the form for creating a post";
    }

//    Create a new post

    @PostMapping(path = "/posts/create")
    @ResponseBody
    public String createPost() {
        return "New post created";
    }
}