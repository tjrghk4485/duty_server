package com.duty_b.duty_server.mapper;
import com.duty_b.duty_server.model.Nurse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface NurseMapper {

    List<Nurse> findById(@Param("id")String id);

    void insertNurse(Nurse user);
}
