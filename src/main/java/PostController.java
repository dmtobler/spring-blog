import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class PostController {

//    Posts index page

    @GetMapping(path = "/posts")
    @ResponseBody
    public String returnAllPosts() {
        return "Posts index page";
    }

//    View an individual post

    @GetMapping(path = "/posts/{id}")
    @ResponseBody
    public String returnPostById(@PathVariable int id) {
        return "Viewing post id #" + id;
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