package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller


public class HomeController {
@GetMapping(path = "/")
    @ResponseBody
    public String Hello() {
    return "Hey I just met you" +
            "This is crazy.." +
            "But here's my number" +
            "So call me maybe"
            ;
}

}
