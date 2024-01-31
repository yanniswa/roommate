package RoomMate.service;

import RoomMate.domain.model.Arbeitsplatz;


import java.util.List;
import java.util.Optional;

public interface ArbeitsplatzRepository {
    List<Arbeitsplatz> getArbeitsplaetze();
    Optional<Arbeitsplatz> getArbeitsplatzByID(int id);

}
