package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String add(@PathVariable double num1, @PathVariable double num2) {
        return num1 + " plus " + num2 + " is " + (num1 + num2) + "!";
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtract(@PathVariable double num1, @PathVariable double num2) {
        return num2 + " minus " + num1 + " is " + (num2 - num1) + "!";
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiply(@PathVariable double num1, @PathVariable double num2) {
        return num1 + " multiplied by " + num2 + " is " + (num1 * num2) + "!";
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divide(@PathVariable double num1, @PathVariable double num2) {
        return num1 + " divided by " + num2 + " is " + (num1 / num2) + "!";
    }
}