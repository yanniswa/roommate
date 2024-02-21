package RoomMate.domain.model;

import RoomMate.annotations.Entity;

import java.util.UUID;

@Entity
public class Benutzer {
    private final String benutzername;
   private UUID id;

    public Benutzer(String benutzername) {
        this.benutzername = benutzername;
    }
     public void setId(UUID id){
        this.id = id;
     }

    public String getBenutzername() {
        return benutzername;
    }

    public UUID getId() {
        return id;
    }
}
