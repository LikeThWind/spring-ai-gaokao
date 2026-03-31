package com.example.modelday1;

import com.example.modelday1.pojo.Reservation;
import com.example.modelday1.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class demo1 {

    @Autowired
    private ReservationService reservationService;

    @Test
    public void addReservation() {
        Reservation reservation = new Reservation(1234567890L, "张三", "男", "12345678901", LocalDateTime.now(), "北京", 5);
        reservationService.addReservation(reservation);
    }

    @Test
    public void getReservationById() {
        Reservation reservationById = reservationService.getReservationById("12345678901");
        System.out.println(reservationById);
    }

    @Test
    public void test() {
        System.out.println();
    }

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 101; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

}
