package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
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



}
