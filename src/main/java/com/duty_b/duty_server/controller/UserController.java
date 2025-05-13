package com.duty_b.duty_server.controller;

import com.duty_b.duty_server.service.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "user")
public class UserController {

    private final NurseService nurseService;


    @GetMapping("/chk")
    public List<Map<String, Object>> getUser(@RequestParam Map<String, Object> params) {
        List<Map<String, Object>> test = nurseService.select("nurseDto.user_chk",params);
        System.out.println("test:" + test);
        return test;
    }

    @PostMapping("/mod")
    public Map<String, Object> userMod(@RequestBody Map<String, Object> params) {
        System.out.println("params=" + params.toString());
        return nurseService.modify("nurseDto.user_register",params);
    }


    @GetMapping("/login")
    public List<Map<String, Object>> login(@RequestParam Map<String, Object> params) {
        System.out.println("params=" + params.toString());
        List<Map<String, Object>> test = nurseService.select("nurseDto.user_login",params);
        System.out.println("test:" + test);
        return test;
    }
}
