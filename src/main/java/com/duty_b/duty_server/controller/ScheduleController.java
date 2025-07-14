package com.duty_b.duty_server.controller;


import com.duty_b.duty_server.model.Nurse;
import com.duty_b.duty_server.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "schedule")
public class ScheduleController {
    @Autowired
    private NurseService nurseService;



    @GetMapping("/sel")
    public List<Map<String, Object>> getSchedule(@RequestParam Map<String, Object> params) {
        return nurseService.select("nurseDto.schedule_sel",params);
    }

    @GetMapping("side/sel")
    public List<Map<String, Object>> getSideSchedule(@RequestParam Map<String, Object> params) {
        return nurseService.select("nurseDto.sideSchedule_sel",params);
    }


    @PostMapping("/mod")
    public Map<String, Object> scheduleMod(@RequestBody List<Map<String, Object>> params) {
        System.out.println("params=" + params.toString());
        return nurseService.modify("nurseDto.schedule_mod",params);
    }

    @PostMapping("/delete")
    public Map<String, Object> deleteAllData(@RequestBody Map<String, Object> params) {
        System.out.println("params=" + params.toString());
        return nurseService.modify("nurseDto.schedule_all_delete",params);
    }


    @PostMapping("/create")
    public Map<String, Object> createDuty(@RequestBody Map<String, Object> params) {
        System.out.println("params=" + params.toString());
        return nurseService.modify("nurseDto.schedule_create",params);
    }

    @PostMapping("/auto")
    public Map<String, Object> autoCreateSchedule(@RequestBody Map<String, Object> params) {
        System.out.println("params=" + params.toString());

        return nurseService.modify("nurseDto.auto_schedule",params);
    }

}


