package RoomMate.database;

import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Buchung;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class ArbeitsplatzRepositoryImpl implements RoomMate.service.ArbeitsplatzRepository {

    private final SpringDataArbeitsplatzRepository repository;


    public ArbeitsplatzRepositoryImpl(SpringDataArbeitsplatzRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Arbeitsplatz> getArbeitsplaetze(){
        return repository.findAll();
    }

    public Optional<Arbeitsplatz> getArbeitsplatzByID(int id){
        return repository.findById(id);
    }

    public List<Buchung> getBuchungen(){
        List<Buchung> buchungen = new ArrayList<>();
        List<Arbeitsplatz> allArbeitsplaetze = this.getArbeitsplaetze();
        allArbeitsplaetze.forEach(e->buchungen.addAll(e.getBuchungen()));
        return buchungen;

    }
}
