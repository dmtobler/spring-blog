package com.codeup.springblog.model;

// TODO: Create a Post class. This class will ultimately represent a POST record from our database. The class should have private properties and getters and setters for a title and body.


public class Post {

    private String title;
    private String body;
    private long id;

    // Empty constructor to allocate memory
    public Post() {}

    // Constructor with title and body
    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    // Constructor with id, title, and body
    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
