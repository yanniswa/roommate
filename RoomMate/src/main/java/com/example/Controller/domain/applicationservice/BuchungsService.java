package com.example.Controller.domain.applicationservice;

import com.example.Controller.database.ArbeitsplatzRepository;
import com.example.Controller.domain.model.Arbeitsplatz;
import com.example.Controller.domain.model.Buchung;
import com.example.Controller.domain.model.Room;
import com.example.Controller.domain.model.Zeitslot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
        return arbeitsplatz.addBuchung(anfang, ende, datum, benutzer);
    }
}
