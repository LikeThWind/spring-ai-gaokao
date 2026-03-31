package com.example.modelday1.service;

import com.example.modelday1.mapper.ReservationMapper;
import com.example.modelday1.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    public void addReservation(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    public Reservation getReservationById(String phone) {
        List<Reservation> reservations = reservationMapper.selectByPrimaryKey(phone);
        if (reservations != null && reservations.size() > 1) {
            return reservations.get(0);
        } else {
            return reservations.get(0);
        }
    }
}
