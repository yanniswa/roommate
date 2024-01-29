package com.example.Controller.domain.model;

import com.example.Controller.annotations.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Room {
    private int roomnumber;

    public Room(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

}
