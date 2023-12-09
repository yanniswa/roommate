package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

   List<Arbeitsplatz> arbeitsplaetze =  List.of(new Arbeitsplatz(List.of("USB","Computer")),new Arbeitsplatz(List.of("Steckdose","Computer")));
    List<Arbeitsplatz> arbeitsplaetze1 =  List.of(new Arbeitsplatz(List.of("USB","Computer")),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor")),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor","LanKabel")));

    List<Room> raume = List.of(new Room(25,arbeitsplaetze), new Room(24,arbeitsplaetze1));
    public void saveRoom(Room room) {

    }

    public List<Room> getAllRooms() {
        return raume;
    }



}

