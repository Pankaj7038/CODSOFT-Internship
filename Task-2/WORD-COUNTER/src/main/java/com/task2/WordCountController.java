package com.task2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
public class WordCountController {

    @RequestMapping("/")
    public String home() {
        return "wordcount";
    }

    @PostMapping("/countWords")
    public String countWords(@RequestParam("textInput") String textInput, @RequestParam("file") MultipartFile file, Model model) {
        String content = "";
        if (!textInput.isEmpty()) {
            content = textInput;
        } else {
            try {
                InputStream fileStream = file.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
                String line;
                while ((line = br.readLine()) != null) {
                    content += line;
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Split the string into words
        String[] words = content.split("[\\s,.!?]+");
        // Initialize a counter for words
        int wordCount = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;
            }
        }
        // Add the word count to the model
        model.addAttribute("wordCount", wordCount);
        return "wordcount";
    }
}
