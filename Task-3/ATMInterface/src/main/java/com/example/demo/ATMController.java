package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ATMController {

    BankAccount userAccount = new BankAccount(); // Creating a new user bank account

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("amount") double amount, Model model) {
        String message;
        if (userAccount.getBalance() >= amount) {
            userAccount.withdraw(amount);
            message = "Withdrawal successful. Current balance: $" + userAccount.getBalance();
        } else {
            message = "Insufficient funds. Current balance: $" + userAccount.getBalance();
        }
        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("amount") double amount, Model model) {
        userAccount.deposit(amount);
        String message = "Deposit successful. Current balance: $" + userAccount.getBalance();
        model.addAttribute("message", message);
        return "result";
    }

    @RequestMapping("/checkBalance")
    public String checkBalance(Model model) {
        String message = "Current balance: $" + userAccount.getBalance();
        model.addAttribute("message", message);
        return "result";
    }
}

