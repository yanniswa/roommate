package com.example.Controller.domain.applicationservice;

import com.example.Controller.domain.model.Arbeitsplatz;
import com.example.Controller.domain.model.Room;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomService {

   List<Arbeitsplatz> arbeitsplaetze =  List.of(new Arbeitsplatz(List.of("USB","Computer"),1),new Arbeitsplatz(List.of("Steckdose","Computer"),2));
    List<Arbeitsplatz> arbeitsplaetze1 =  List.of(new Arbeitsplatz(List.of("USB","Computer"),3),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor"),4),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor","LanKabel"),5));

    List<Room> raume = List.of(new Room(25,arbeitsplaetze), new Room(24,arbeitsplaetze1));
    public void saveRoom(Room room) {

    }

    public List<Room> getAllRooms() {
        return raume;
    }

    public Room getRoomByRoomNumber(int roomNumber){
        Optional<Room> optionalRoom = raume.stream().filter(e -> e.getRoomnumber() == roomNumber)
                .findAny();
        return optionalRoom.get();
    }

    public boolean addBuchungToArbeitsplatz(){
        return true;
    }

}

