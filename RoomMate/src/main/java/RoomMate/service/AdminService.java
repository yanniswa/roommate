package RoomMate.service;

import RoomMate.database.ArbeitsplatzRepositoryImpl;
import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Buchung;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private final ArbeitsplatzRepository repository;

    public AdminService(ArbeitsplatzRepository repository) {
        this.repository = repository;
    }

}
