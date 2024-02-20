package RoomMate.service;

import RoomMate.database.ArbeitsplatzRepositoryImpl;
import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Buchung;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminService {
    private final ArbeitsplatzRepository repository;

    public AdminService(ArbeitsplatzRepository repository) {
        this.repository = repository;
    }

    public Arbeitsplatz addArbeitsplatz(int raumnummer, Set<String> ausstattung) {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(raumnummer, ausstattung);
        Arbeitsplatz gespeicherterArbeitsplatz = repository.save(arbeitsplatz);
        return gespeicherterArbeitsplatz;
    }
}
