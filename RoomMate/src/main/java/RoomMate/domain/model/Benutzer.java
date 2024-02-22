package RoomMate.domain.model;

import RoomMate.annotations.Entity;

import java.util.UUID;

@Entity
public class Benutzer {
    private final String benutzername;

    public Benutzer(String benutzername) {
        this.benutzername = benutzername;
    }


    public String getBenutzername() {
        return benutzername;
    }


}
