package RoomMate.service;

import RoomMate.database.ArbeitsplatzRepositoryImpl;
import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Buchung;
import RoomMate.domain.model.Zeitslot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuchungsService {

    private final ArbeitsplatzRepository repository;

    public BuchungsService(ArbeitsplatzRepository repository) {
        this.repository = repository;
    }


    public List<Zeitslot> freieZeitslot(LocalDate datum,int platzId){
        Optional<Arbeitsplatz> arbeitsplatzByID = repository.getArbeitsplatzByID(platzId);
        return arbeitsplatzByID.get().freieZeitslots(datum);
    }
    public Arbeitsplatz getArbeitsplatz(int platzID){
        return repository.getArbeitsplatzByID(platzID).get();
    }
    public boolean addBuchungToArbeitsplatz(int platzID,LocalTime anfang, LocalTime ende,
                                            LocalDate datum, String benutzer){
        Arbeitsplatz arbeitsplatz = repository.getArbeitsplatzByID(platzID).get();
        boolean f = arbeitsplatz.addBuchung(anfang, ende, datum, benutzer);
        repository.save(arbeitsplatz);
        return f;
    }

    public List<Arbeitsplatz> alleArbeitsplaetze(){
        return repository.getArbeitsplaetze();
    }

    public List<Buchung> alleBuchungen(){
        List<Arbeitsplatz> arbeitsplaetze = repository.getArbeitsplaetze();
        List<Buchung> buchungen = new ArrayList<>();
        arbeitsplaetze.forEach(e->buchungen.addAll(e.getBuchungen()));
        return buchungen;
    }


}
