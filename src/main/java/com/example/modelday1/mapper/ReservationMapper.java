package com.example.modelday1.mapper;

import com.example.modelday1.pojo.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReservationMapper {
    @Insert( "insert into reservation(NAME,gender,phone,communication_time,province,estimated_score) values(#{name},#{gender},#{phone},#{communicationTime},#{province},#{estimatedScore})")
    void insert(Reservation record);
    @Select( "select * from reservation where phone = #{phone}")
    List<Reservation> selectByPrimaryKey(String phone);


}
