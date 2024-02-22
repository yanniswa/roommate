package RoomMate.database.api;

import org.springframework.data.annotation.Id;

public record KeymasterRaum(@Id Integer id, String uuid, String room) {
}
