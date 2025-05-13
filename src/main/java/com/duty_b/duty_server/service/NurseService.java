package com.duty_b.duty_server.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class NurseService {


    private final BaseService baseService;

    public NurseService(BaseService baseService) {
        this.baseService = baseService;
    }

    @Transactional
    public List<Map<String, Object>> select(String name, Map<String, Object> params) {
        return baseService.select(name,params);
    }

    @Transactional
    public List<Map<String, Object>> select(String name,long params) {
        return baseService.select(name,params);
    }
    @Transactional
    public Map<String, Object> modify(String name, List<Map<String, Object>> params) {
        return baseService.modify(name,params);
    }
    @Transactional
    public Map<String, Object> modify(String name, Map<String, Object> param) {
        return baseService.modify(name,param);
    }


}
