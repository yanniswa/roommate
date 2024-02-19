package RoomMate.database;

import RoomMate.domain.model.Arbeitsplatz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringDataArbeitsplatzRepository extends CrudRepository<Arbeitsplatz,Integer> {
    List<Arbeitsplatz> findAll();
}
