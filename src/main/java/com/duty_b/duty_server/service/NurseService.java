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

    public Map<String, Object> modify(String name,List<Map<String, Object>> params) {
        Map<String, Object> returnnurseParams = null;
        /*for (Map<String, Object> nurseParams : params) {

            try {
                *//*nurseParams.put("output_val", 10);          // OUT 파라미터 (초기 값은 0)
                nurseParams.put("output_cnt", 10);           // OUT 파라미터 (초기 값은 0)
                *//**//*nurseParams.put("output_msg", "test"); *//*         // OUT 파라미터 (초기 값은 빈 문자열)
                System.out.println("nurseParams=" + nurseParams);
                sqlSessionTemplate.insert(name, nurseParams);
                System.out.println("output_msg=" + nurseParams.get("output_msg"));
                returnnurseParams = nurseParams;
                return returnnurseParams;
            } catch (Exception e) {
                e.printStackTrace();
                // 예외 발생 시 처리
                returnnurseParams = nurseParams;
                return returnnurseParams;
            }*/
            try {
                for (Map<String, Object> nurseParams : params) {
                    System.out.println("nurseParams=" + nurseParams);
                    sqlSessionTemplate.insert(name, nurseParams);
                    System.out.println("output_msg=" + nurseParams.get("output_msg"));
                    returnnurseParams = nurseParams;
                    if(nurseParams.get("output_msg") != "저장되었습니다"){
                        /*break;*/
                    }
                }
            }catch (Exception e){
                returnnurseParams.put("output_msg","에러발생!");
            }


        return returnnurseParams;
    }
}
