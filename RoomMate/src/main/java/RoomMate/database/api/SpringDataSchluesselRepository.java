package RoomMate.database.api;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataSchluesselRepository extends CrudRepository<KeymasterSchluessel,Integer> {
    Optional<KeymasterSchluessel> findByUuid(String id);
    Optional<KeymasterSchluessel> findByOwner(String owner);
}
