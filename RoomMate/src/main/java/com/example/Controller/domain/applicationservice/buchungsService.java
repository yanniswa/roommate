package com.example.Controller.domain.applicationservice;

import com.example.Controller.domain.model.Arbeitsplatz;
import com.example.Controller.domain.model.Buchung;
import com.example.Controller.domain.model.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class buchungsService {
    private int buchungsID = 0;
    private final RoomService roomService;
    private final Arbeitsplatz arbeitsplatz = null;
    private Room raum = null;

    public buchungsService(RoomService roomService) {
        this.roomService = roomService;
    }

    private Arbeitsplatz getArbeitsplatz(int platzID){
        return raum.getArbeitsplaetze().stream().filter(e->e.getId()==platzID).findFirst().get();
    }
    public boolean addBuchungToArbeitsplatz(int platzID, int roomNumber, LocalTime anfang, LocalTime ende,
                                            LocalDate datum, String benutzer){
        System.out.println(benutzer);
        raum = roomService.getRoomByRoomNumber(roomNumber);
        List<Arbeitsplatz> raumArbeitsplaetze = raum.getArbeitsplaetze();
        Arbeitsplatz arbeitsplatz = getArbeitsplatz(platzID);
        List<Buchung> buchungen = arbeitsplatz.getBuchungen();
        boolean keineBuchungenImZeitraum = buchungen.stream().filter(e -> e.getLocalDate().isEqual(datum))
                .filter(e -> e.getAnfang().isBefore(anfang) && e.getEnde().isAfter(anfang) ||
                        e.getAnfang().isAfter(anfang)&& e.getEnde().isAfter(anfang))
                .filter(e -> e.getAnfang().isBefore(ende) && e.getEnde().isAfter(ende)||
                        e.getAnfang().isBefore(ende) && e.getEnde().isBefore(ende))
                .toList().isEmpty();
        if(!keineBuchungenImZeitraum){
            return false;
        }
        arbeitsplatz.addBuchungen(new Buchung(buchungsID,datum,anfang,ende,benutzer));
        buchungsID++;
        return true;
    }
}
