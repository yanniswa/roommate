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

    List<Arbeitsplatz> arbeitsplaetze =  List.of(new Arbeitsplatz(Set.of("USB","Computer"),1,25),new Arbeitsplatz(Set.of("Steckdose","Computer"),2,25),
    new Arbeitsplatz(Set.of("USB","Computer"),3,24),new Arbeitsplatz(Set.of("Steckdose","Computer","Monitor"),4,24),new Arbeitsplatz(Set.of("Steckdose","Computer","Monitor","LanKabel"),5,24));

    public ArbeitsplatzRepositoryImpl(SpringDataArbeitsplatzRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Arbeitsplatz> getArbeitsplaetze(){
        return repository.findAll();
    }

    public Optional<Arbeitsplatz> getArbeitsplatzByID(int id){
        return arbeitsplaetze.stream().filter(e->e.getId()==id).findFirst();
    }

    public List<Buchung> getBuchungen(){
        List<Buchung> buchungen = new ArrayList<>();
        buchungen.add(new Buchung(LocalDate.of(2024,02,24), LocalTime.of(18,30), LocalTime.of(20,30), "tobi"));
        arbeitsplaetze.forEach(e->buchungen.addAll(e.getBuchungen()));
        return buchungen;
        // Dummy Werte werden zurück gegeben
    }
}
