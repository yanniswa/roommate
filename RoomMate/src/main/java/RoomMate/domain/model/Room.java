package RoomMate.domain.model;

import RoomMate.annotations.Entity;

import java.util.UUID;

@Entity
public class Room {
    private final int roomnumber;
    private UUID id;

    public Room(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setId(UUID id){
        this.id=id;
    }

    public UUID getId() {
        return id;
    }
}
