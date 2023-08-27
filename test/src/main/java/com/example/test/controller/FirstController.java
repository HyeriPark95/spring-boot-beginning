package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String noToMeetYou(Model model){
        model.addAttribute("username","오쏠");
        System.out.println("반갑");
        return "greetings";
    }
    @GetMapping("/bye")
    public String seeYou(Model model){
        model.addAttribute("nickname","박혜리");
        return "goodbye";
    }
}
