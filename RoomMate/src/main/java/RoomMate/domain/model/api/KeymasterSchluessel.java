package RoomMate.domain.model.api;

public record KeymasterSchluessel(Integer id,String uuid,String owner) {
    public KeymasterSchluessel(Integer id, String uuid, String owner) {
        this.id = id;
        this.uuid = uuid;
        this.owner = owner;
    }

    public KeymasterSchluessel(String uuid, String owner) {
        this(null, uuid, owner);
    }
}
