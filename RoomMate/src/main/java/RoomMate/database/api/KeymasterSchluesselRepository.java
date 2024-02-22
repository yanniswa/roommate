package RoomMate.database.api;

import RoomMate.database.api.KeymasterSchluessel;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class KeymasterSchluesselRepository implements RoomMate.service.api.KeymasterSchluesselRepository {

    SpringDataSchluesselRepository repository;

    public KeymasterSchluesselRepository(SpringDataSchluesselRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomMate.domain.model.api.KeymasterSchluessel save(RoomMate.domain.model.api.KeymasterSchluessel d) {
        return convertSchluessel(repository.save(extractSchluessel(d)));
    }

    @Override
    public Optional<RoomMate.domain.model.api.KeymasterSchluessel> findByUuid(String uuid) {
        return convertSchluessel(repository.findByUuid(uuid));
    }

    @Override
    public Optional<RoomMate.domain.model.api.KeymasterSchluessel> findByName(String name) {
        return convertSchluessel(repository.findByOwner(name));
    }

    private RoomMate.domain.model.api.KeymasterSchluessel convertSchluessel(KeymasterSchluessel schluessel){
        return new RoomMate.domain.model.api.KeymasterSchluessel(schluessel.id(),schluessel.uuid(),schluessel.owner());
    }
    private Optional<RoomMate.domain.model.api.KeymasterSchluessel> convertSchluessel(Optional<KeymasterSchluessel> schluessel){
        if(schluessel.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(new RoomMate.domain.model.api.KeymasterSchluessel(schluessel.get().id(),schluessel.get().uuid(),schluessel.get().owner()));
    }
    private KeymasterSchluessel extractSchluessel(RoomMate.domain.model.api.KeymasterSchluessel schluessel){
        return new KeymasterSchluessel(schluessel.id(), schluessel.uuid(), schluessel.owner());
    }
}
