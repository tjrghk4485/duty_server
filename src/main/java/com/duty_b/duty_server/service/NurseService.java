package com.duty_b.duty_server.service;

import com.duty_b.duty_server.mapper.NurseMapper;
import com.duty_b.duty_server.model.Nurse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NurseService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;



    public List<Map<String, Object>> select(String name,Map<String, Object> params) {
        return sqlSessionTemplate.selectList(name,params);
    }

    public List<Map<String, Object>> modify(String name,Map<String, Object> params) {
        return sqlSessionTemplate.selectList(name,params);
    }




}
