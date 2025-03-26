package com.duty_b.duty_server.controller;

import com.duty_b.duty_server.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private NurseService nurseService;


    @GetMapping("/chk")
    public List<Map<String, Object>> getNurse(@RequestParam Map<String, Object> params) {
        System.out.println("params=" + params.toString());
        return nurseService.select("nurseDto.user_chk",params);
    }

    @PostMapping("/mod")
    public Map<String, Object> nurseMod(@RequestBody List<Map<String, Object>> params) {
        System.out.println("params=" + params.toString());
        return nurseService.modify("nurseDto.schedule_mod",params);
    }
}
