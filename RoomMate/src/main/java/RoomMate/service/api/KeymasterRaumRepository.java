package RoomMate.service.api;

import RoomMate.domain.model.api.KeymasterRaum;

import java.util.Optional;
import java.util.UUID;

public interface KeymasterRaumRepository {

    KeymasterRaum save(KeymasterRaum master);

    Optional<KeymasterRaum> findByuuid(String id);

    Optional<KeymasterRaum> findByRoom(String raum);
}