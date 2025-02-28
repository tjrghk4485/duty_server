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

    public void modify(String name,List<Map<String, Object>> params) {

        for (Map<String, Object> nurseParams : params) {
            try {
                System.out.println("nurseParams=" + nurseParams);
                sqlSessionTemplate.selectOne(name, nurseParams);

                Integer outputVal = (Integer) nurseParams.get("output_val");
                Integer outputCnt = (Integer) nurseParams.get("output_cnt");
                String outputMsg = (String) nurseParams.get("output_msg");

                // OUT 파라미터 값 로그에 출력
                System.out.println("Nurse ID: " + nurseParams.get("nurse_id"));
                System.out.println("Output Value: " + outputVal);
                System.out.println("Output Count: " + outputCnt);
                System.out.println("Output Message: " + outputMsg);
            }
        catch(Exception e){
            e.printStackTrace();
            // 예외 발생 시 처리

        }

    }
    }




}
