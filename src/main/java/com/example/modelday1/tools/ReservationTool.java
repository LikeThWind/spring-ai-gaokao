package com.example.modelday1.tools;

import com.example.modelday1.pojo.Reservation;
import com.example.modelday1.service.ReservationService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationTool {

    @Autowired
    private ReservationService reservationService;


    @Tool("预约志愿填报服务")
    public void addReservation(
            @P("考生姓名") String name,
            @P("考生性别") String gender,
            @P("考生手机号") String phone,
            @P("预约沟通时间,格式为:yyyy-MM-dd'T'HH:mm") String communicationTime,
            @P("考生所在省份") String province,
            @P("考生预估分数") Integer estimatedScore) {
        Reservation reservation = new Reservation(null, name, gender, phone, LocalDateTime.now(), province, estimatedScore);
        reservationService.addReservation(reservation);
    }

//2.工具方法：查询预约信息家√

    @Tool("根据考生手机号查询预约单")
    public Reservation findReseryation(@P("考生手机号") String phone) {
        return reservationService.getReservationById(phone);

    }

}
