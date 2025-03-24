package com.duty_b.duty_server.service;

import com.duty_b.duty_server.mapper.NurseMapper;
import com.duty_b.duty_server.model.Nurse;
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

@Service
public class NurseService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public List<Map<String, Object>> select(String name,Map<String, Object> params) {
        return sqlSessionTemplate.selectList(name,params);
    }

   /* public Map<String, Object> modify(String name, List<Map<String, Object>> params) {
        Map<String, Object> returnnurseParams = new HashMap<>();;
        TransactionStatus tsts = null;
        try {

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            tsts = transactionManager.getTransaction(def);

            for (Map<String, Object> param : params){
                sqlSessionTemplate.update(name, param);
                returnnurseParams.put("output_msg",param.get("output_msg"));
                System.out.println("param.get(\"output_msg\")" + param.get("output_msg"));
                if(!param.get("output_msg").equals("저장되었습니다")){
                    returnnurseParams.put("output_msg",param.get("output_msg"));
                    break;
                }
            }
            transactionManager.commit(tsts);
        } catch (Exception e) {
            transactionManager.rollback(tsts);
            returnnurseParams.put("output_msg", "에러발생! " + e.getMessage());
            e.printStackTrace();

        }
        return returnnurseParams;
    }*/

    public Map<String, Object> modify(String name, List<Map<String, Object>> params) {
        Map<String, Object> returnnurseParams = new HashMap<>();
        SqlSession sqlSession = null;
        TransactionStatus tsts = null;
        try {
            sqlSession = sqlSessionFactory.openSession();  // 새로운 세션 생성
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);  // 기본 트랜잭션 전파
            tsts = transactionManager.getTransaction(def);

            for (Map<String, Object> param : params) {
                sqlSession.update(name, param);
                returnnurseParams.put("output_msg", param.get("output_msg"));
                System.out.println("param.get(\"output_msg\")" + param.get("output_msg"));

                if (!param.get("output_msg").equals("저장되었습니다")) {
                    returnnurseParams.put("output_msg", param.get("output_msg"));
                    break;
                }
            }

            transactionManager.commit(tsts);
        } catch (Exception e) {
            if (tsts != null) {
                transactionManager.rollback(tsts);
            }
            returnnurseParams.put("output_msg", "에러발생! " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();  // 세션 종료
            }
        }
        return returnnurseParams;
    }

    public Map<String, Object> modify(String name, Map<String, Object> param) {
        Map<String, Object> returnnurseParams = new HashMap<>();
        SqlSession sqlSession = null;
        TransactionStatus tsts = null;
        try {
            sqlSession = sqlSessionFactory.openSession();  // 새로운 세션 생성
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);  // 기본 트랜잭션 전파
            tsts = transactionManager.getTransaction(def);


                sqlSession.update(name, param);
                returnnurseParams.put("output_msg", param.get("output_msg"));
                System.out.println("param.get(\"output_msg\")" + param.get("output_msg"));

                if (!param.get("output_msg").equals("저장되었습니다")) {
                    returnnurseParams.put("output_msg", param.get("output_msg"));

                }


            transactionManager.commit(tsts);
        } catch (Exception e) {
            if (tsts != null) {
                transactionManager.rollback(tsts);
            }
            returnnurseParams.put("output_msg", "에러발생! " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();  // 세션 종료
            }
        }
        return returnnurseParams;
    }


}
