package com.task1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Random;

@Controller
public class RandomNumberController {

    private int randomNumber;
    private int userGuess;
    private boolean submitted = false;

    @GetMapping("/")
    public String getRandomNumber(Model model) {
        int min = 1;
        int max = 100;
        randomNumber = generateRandomNumber(min, max);
        model.addAttribute("randomNumber", randomNumber);
        return "random";
    }

    @PostMapping("/")
    public String checkGuess(int userGuess, Model model) {
        this.userGuess = userGuess;
        submitted = true;
        String result;
        if (userGuess == randomNumber) {
            result = "correct";
        } else if (userGuess > randomNumber) {
            result = "high";
        } else {
            result = "low";
        }
        model.addAttribute("result", result);
        model.addAttribute("userGuess", this.userGuess);
        model.addAttribute("submitted", submitted);
        model.addAttribute("randomNumber", randomNumber);
   
        return "random";
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}



