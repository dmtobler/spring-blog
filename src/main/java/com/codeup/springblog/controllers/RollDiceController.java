package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String showRollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{num}")
    public String rollDice(@PathVariable int num, Model model) {
        int randomNum = (int) (1 + (Math.random() * 6));
        model.addAttribute("num", "Your number: " + num);
        model.addAttribute("randomNum", "Dice roll: " + randomNum);

        if(num != 0) {
            if(randomNum == num) {
                model.addAttribute("correctGuess", true);
            } else {
                model.addAttribute("correctGuess", false);
            }
        }
        return "roll-dice";
    }
}