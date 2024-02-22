package RoomMate.domain.model.api;

public record KeymasterRaum(Integer id, String uuid,String room){
    public KeymasterRaum(Integer id, String uuid, String room) {
        this.id = id;
        this.uuid = uuid;
        this.room = room;
    }

    public KeymasterRaum(String uuid, String raum) {
        this(null, uuid, raum);
    }
}
