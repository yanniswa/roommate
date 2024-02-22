package RoomMate.domain.model;

import RoomMate.annotations.Entity;

import java.util.UUID;

@Entity
public class Room {
    private final int roomnumber;

    public Room(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getRoomnumber() {
        return roomnumber;
    }




}
