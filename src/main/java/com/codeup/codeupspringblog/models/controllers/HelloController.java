package com.codeup.codeupspringblog.models.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

record Message (String message){}
@Controller
public class HelloController {
    //Mapping for GetRequest
    //Method will listen for GET requests at /hello
@GetMapping("/hello")
@ResponseBody
public String helloWorld() {
    return "Hello From Spring!";
}
@GetMapping(path = "/json", produces = "application/json")
    @ResponseBody
public Message returnMessage() {
    return new Message("Hello from Springgy Spring!");
}
@RequestMapping(path = "/color", method = RequestMethod.GET)
    @ResponseBody
    public String color() {
    return "Royal Blue!";
}

@GetMapping("/hello/{firstName}")
    @ResponseBody
    public String sayHello(@PathVariable String firstName){
    return "<h1>Hello " + firstName + "</h1>";
}

    @GetMapping("/hello/{name}")
    public String helloWorld(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }
}
