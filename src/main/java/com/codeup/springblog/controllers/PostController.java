package com.codeup.springblog.controllers;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.model.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {

    //******************************
    //**** DEPENDENCY INJECTION ****
    //******************************

    // For PostRepo
    private final PostRepository postDao;

    // For UserRepo
    private final UserRepository userDao;

    // For emailService
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailservice) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailservice;
    }


    //******************************
    //******* POST INDEX PAGE ******
    //******************************

    @GetMapping("/posts")
    public String returnAllPosts(Model model) {

        // Get all the posts with the built in JPA functionality!
        model.addAttribute("posts", postDao.findAll());

        // Return the view
        return "posts/index";
    }


    //******************************
    //*** IND. POST PAGE (BY ID) ***
    //******************************

    @GetMapping(path = "/posts/{id}")
    // Pass id, title, and body variables to the controller
    public String returnPostById(@PathVariable long id, Model model) {

        // Get post by ID
        model.addAttribute("post", postDao.getPostById(id));

        // Return the view
        return "posts/show";
    }


    //******************************
    //******** CREATE POST *********
    //******************************

    // View the form to create a post
    @GetMapping(path = "/posts/create")
    public String viewCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // Create the post
    @PostMapping(path = "/posts/create")
    public String createPost(
        @ModelAttribute
        @Valid Post post,
        Errors validation,
        Model model) {
            // Check if errors present given title and body requirements defined in Post.java
            if (validation.hasErrors()) {
                model.addAttribute("errors", validation);
                model.addAttribute("post", post);

                // If errors present, reload the page with the errors listed as alerts above the form.
                return "posts/create";
            }

        // If no errors, assign the user to the post...
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);

        // Send an email confirmation using the emailService...
        String emailSubject = "New Post Created";
        String emailBody = "Successfully posted new blog post titled \"" + post.getTitle() + "\"!";
        emailService.prepareAndSend(post, emailSubject, emailBody);

        // Save the post and redirect to posts index
        postDao.save(post);
        return "redirect:/posts";
    }


    //******************************
    //********* EDIT POST **********
    //******************************

    // View the post info to be edited
    @GetMapping("/posts/{id}/edit")
    public String postEditForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    // Submit the edited post information and update the DB
    @PostMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {

        // Get the post from DB
        Post post = postDao.getPostById(id);

        // Set the title to the value in the title field
        post.setTitle(title);

        // Set the body to the value in the body field
        post.setBody(body);

        // Update the post in the DB
        postDao.save(post);

        // Redirect to the posts index
        return "redirect:/posts";
    }

    //******************************
    //******** DELETE POST *********
    //******************************

    @PostMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id) {
        // Grab the post by the ID
        postDao.deleteById(id);

        // Redirect to post index
        return "redirect:/posts";
    }
}