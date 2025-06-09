package com.duty_b.duty_server.service;


import com.duty_b.duty_server.model.Nurse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
@Service
public class BaseService {

    private final SqlSessionTemplate sqlSessionTemplate;


    public List<Map<String, Object>> select(String name, Map<String, Object> params) {
        try {
            return sqlSessionTemplate.selectList(name, params);
        } catch (Exception e) {
            // 로깅은 무조건 필수
            log.error("DB select 오류 - name: {}, params: {}, error: {}", name, params, e.getMessage(), e);
            // 커스텀 예외로 변환해서 던짐
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, Object>> select(String name,long params) {
        try {
            return sqlSessionTemplate.selectList(name, params);
        } catch (Exception e) {
            // 로깅은 무조건 필수
            log.error("DB select 오류 - name: {}, params: {}, error: {}", name, params, e.getMessage(), e);
            // 커스텀 예외로 변환해서 던짐
            throw new RuntimeException(e);
        }
    }


    public Map<String, Object> modify(String name, List<Map<String, Object>> params) {
        Map<String, Object> returnnurseParams = new HashMap<>();
        try {
            for (Map<String, Object> param : params) {
                sqlSessionTemplate.update(name, param);
                returnnurseParams.put("output_msg", param.get("output_msg"));
                System.out.println("param.get(\"output_msg\")" + param.get("output_msg"));

                if (!param.get("output_msg").equals("저장되었습니다")) {
                    returnnurseParams.put("output_msg", param.get("output_msg"));
                    throw new RuntimeException("저장 실패: " + param.get("output_msg"));

                }
            }
            return returnnurseParams;
        }catch (Exception e) {
            // 로깅은 무조건 필수
            log.error("DB select 오류 - name: {}, params: {}, error: {}", name, params, e.getMessage(), e);
            // 커스텀 예외로 변환해서 던짐
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> modify(String name, Map<String, Object> param) {
        Map<String, Object> returnnurseParams = new HashMap<>();
        try {
            sqlSessionTemplate.update(name, param);
            returnnurseParams.put("output_msg", param.get("output_msg"));
            System.out.println("param.get(\"output_msg\")" + param.get("output_msg"));

            if (!param.get("output_msg").equals("저장되었습니다")) {
                returnnurseParams.put("output_val", param.get("output_val"));
                returnnurseParams.put("output_msg", param.get("output_msg"));
                throw new RuntimeException("저장 실패: " + param.get("output_msg"));

            }

            return returnnurseParams;
        }catch (Exception e) {
            // 로깅은 무조건 필수
            log.error("DB select 오류 - name: {}, params: {}, error: {}", name, param, e.getMessage(), e);
            // 커스텀 예외로 변환해서 던짐
            throw new RuntimeException(e);
        }
    }


}
