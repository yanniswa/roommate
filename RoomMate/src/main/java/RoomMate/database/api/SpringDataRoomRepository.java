package RoomMate.database.api;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataRoomRepository extends CrudRepository<KeymasterRaum,Integer> {

    Optional<KeymasterRaum> findByUuid(String id);

    Optional<KeymasterRaum>findByRoom(String room);

}