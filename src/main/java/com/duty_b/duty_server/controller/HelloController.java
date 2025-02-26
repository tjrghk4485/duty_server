package com.duty_b.duty_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello-template")
    @ResponseBody
    public String helloString(@RequestParam(value = "name", defaultValue = "Guest") String name) {
        return "index";
    }
}


