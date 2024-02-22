package RoomMate.database.api;

import RoomMate.service.api.KeymasterRaumRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KeymasterRaumRepositoryImpl implements KeymasterRaumRepository {

    SpringDataRoomRepository repository;

    public KeymasterRaumRepositoryImpl(SpringDataRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomMate.domain.model.api.KeymasterRaum save(RoomMate.domain.model.api.KeymasterRaum master) {
        return convertRaum(repository.save(extract(master)));
    }

    @Override
    public Optional<RoomMate.domain.model.api.KeymasterRaum> findByuuid(String id) {
        return convertRaum(repository.findByUuid(id));
    }

    @Override
    public Optional<RoomMate.domain.model.api.KeymasterRaum> findByRoom(String raum) {
        return convertRaum(repository.findByRoom(raum));
    }

    @Override
    public List<RoomMate.domain.model.api.KeymasterRaum> findAll() {
        return convertRaumist(repository.findAll());
    }

    private RoomMate.domain.model.api.KeymasterRaum convertRaum(KeymasterRaum raum){
        return new RoomMate.domain.model.api.KeymasterRaum(raum.id(), raum.uuid(), raum.room());
    }
    private Optional<RoomMate.domain.model.api.KeymasterRaum> convertRaum(Optional<KeymasterRaum> raum){
        if(raum.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(new RoomMate.domain.model.api.KeymasterRaum(raum.get().id(), raum.get().uuid(), raum.get().room()));
    }
    private List<RoomMate.domain.model.api.KeymasterRaum> convertRaumist(List<KeymasterRaum> raum){
        return raum.stream().map(this::convertRaum).toList();
    }

    private KeymasterRaum extract(RoomMate.domain.model.api.KeymasterRaum raum ){
        return new KeymasterRaum(raum.id(), raum.uuid(), raum.room());
    }
}
