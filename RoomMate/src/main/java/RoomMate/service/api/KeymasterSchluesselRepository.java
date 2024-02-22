package RoomMate.service.api;

import RoomMate.domain.model.api.KeymasterSchluessel;

import java.util.List;
import java.util.Optional;

public interface KeymasterSchluesselRepository {

    KeymasterSchluessel save(KeymasterSchluessel d);

    Optional<KeymasterSchluessel> findByUuid(String uuid);

    Optional<KeymasterSchluessel> findByName(String name);

    List<KeymasterSchluessel> findAll();
}
