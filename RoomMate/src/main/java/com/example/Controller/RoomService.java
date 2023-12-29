package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

   List<Arbeitsplatz> arbeitsplaetze =  List.of(new Arbeitsplatz(List.of("USB","Computer"),1),new Arbeitsplatz(List.of("Steckdose","Computer"),2));
    List<Arbeitsplatz> arbeitsplaetze1 =  List.of(new Arbeitsplatz(List.of("USB","Computer"),1),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor"),2),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor","LanKabel"),3));

    List<Room> raume = List.of(new Room(25,arbeitsplaetze), new Room(24,arbeitsplaetze1));
    public void saveRoom(Room room) {

    }

    public List<Room> getAllRooms() {
        return raume;
    }


}

