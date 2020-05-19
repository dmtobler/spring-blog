package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/posts";
    }

    @GetMapping("/home")
    public String welcome() {
        return "redirect:/posts";
    }
}