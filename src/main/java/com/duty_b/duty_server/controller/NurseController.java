package com.duty_b.duty_server.controller;

import com.duty_b.duty_server.model.Nurse;
import com.duty_b.duty_server.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "bm/nurse")
public class NurseController {
    @Autowired
    private NurseService nurseService;



    @GetMapping("/sel")
    public List<Map<String, Object>> getNurse(@RequestParam Map<String, Object> params) {

        return nurseService.select("nurseDto.nurse_sel",params);
    }

    @PostMapping("/mod")
    public Map<String, Object> nurseMod(@RequestBody List<Map<String, Object>> params) {
        System.out.println("params=" + params.toString());
        return nurseService.modify("nurseDto.nurse_mod",params);
    }

}
