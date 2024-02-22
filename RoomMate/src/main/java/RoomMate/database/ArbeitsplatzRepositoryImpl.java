package RoomMate.database;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ArbeitsplatzRepositoryImpl implements RoomMate.service.ArbeitsplatzRepository {

    private final SpringDataArbeitsplatzRepository repository;



    public ArbeitsplatzRepositoryImpl(SpringDataArbeitsplatzRepository repository) {
        this.repository = repository;
    }

    private RoomMate.domain.model.Arbeitsplatz convertArbeitsplatz(Arbeitsplatz arbeitsplatz){
        RoomMate.domain.model.Arbeitsplatz result = new RoomMate.domain.model.Arbeitsplatz(arbeitsplatz.ausstattung(), arbeitsplatz.Id(),convertRoom(arbeitsplatz.room()));
        arbeitsplatz.buchungen().forEach(d->result.addBuchung(convertBuchung(d)));
        return result;
    }
    private RoomMate.domain.model.Buchung convertBuchung(Buchung buchung){
        return new RoomMate.domain.model.Buchung(buchung.localDate(), buchung.anfang(), buchung.ende(), convertBenutzer(buchung.benutzer()),buchung.arbeitsplatz(),buchung.Id());
    }
    private RoomMate.domain.model.Room convertRoom(Room room){
        return new RoomMate.domain.model.Room(room.roomnumber());
    }
    private RoomMate.domain.model.Benutzer convertBenutzer(Benutzer benutzer){
        RoomMate.domain.model.Benutzer benutzer1 = new RoomMate.domain.model.Benutzer(benutzer.benutzername());
        return benutzer1;
    }
    @Override
    public List<RoomMate.domain.model.Arbeitsplatz> getArbeitsplaetze(){
        List<Arbeitsplatz> all = repository.findAll();
        return all.stream().map(this::convertArbeitsplatz).toList();

    }

    public Optional<RoomMate.domain.model.Arbeitsplatz> getArbeitsplatzByID(int id){
        return repository.findById(id).map(this::convertArbeitsplatz);
    }
    private Buchung extractBuchung(RoomMate.domain.model.Buchung buchung){
        return new Buchung(buchung.getID(),buchung.getLocalDate(),buchung.getAnfang(),buchung.getEnde(),extractBenutzer((buchung.getBenutzer())), buchung.getArbeitsplatzid());
    }
    private Room extractRoom(RoomMate.domain.model.Room room){
        return new Room(room.getRoomnumber());
    }

    private Benutzer extractBenutzer (RoomMate.domain.model.Benutzer benutzer){return new Benutzer(benutzer.getBenutzername());}

    @Override
    public RoomMate.domain.model.Arbeitsplatz save(RoomMate.domain.model.Arbeitsplatz arbeitsplatz) {
        List<Buchung> buchungen = arbeitsplatz.getBuchungen().stream().map(this::extractBuchung).toList();
        Room room = new Room(arbeitsplatz.getRaumnummer());
        Arbeitsplatz arbeitsplatzDTO = new Arbeitsplatz(arbeitsplatz.getId(), buchungen, room, arbeitsplatz.getAusstattung().stream().collect(Collectors.toSet()));
        Arbeitsplatz save = repository.save(arbeitsplatzDTO);
        return convertArbeitsplatz(save);

    }


    /**
    public List<RoomMate.domain.model.Buchung> getBuchungen(){
        List<RoomMate.domain.model.Buchung> buchungen = new ArrayList<>();
        List<RoomMate.domain.model.Arbeitsplatz> allArbeitsplaetze = this.getArbeitsplaetze();
        allArbeitsplaetze.forEach(e->buchungen.addAll(e.getBuchungen()));
        return buchungen;

    }**/
}
