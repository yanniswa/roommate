package RoomMate.database.api;

import org.springframework.data.annotation.Id;

public record KeymasterSchluessel(@Id Integer id,String uuid,String owner) {
}
