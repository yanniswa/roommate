package com.example.Controller.domain.applicationservice;

import com.example.Controller.domain.model.Arbeitsplatz;
import com.example.Controller.domain.model.Buchung;
import com.example.Controller.domain.model.Room;
import com.example.Controller.domain.model.Zeitslot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BuchungsService {
    private int buchungsID = 0;
    private final RoomService roomService;
    private final Arbeitsplatz arbeitsplatz = null;
    private Room raum = null;

    public BuchungsService(RoomService roomService) {
        this.roomService = roomService;
    }


    public List<Zeitslot> freieZeitslot(LocalDate datum,int roomnumber, int platzId){
        Room room = roomService.getRoomByRoomNumber(roomnumber);
        return room.freieSlots(datum,platzId);
    }
    private Arbeitsplatz getArbeitsplatz(int platzID){
        return raum.getArbeitsplaetze().stream().filter(e->e.getId()==platzID).findFirst().get();
    }
    public boolean addBuchungToArbeitsplatz(int platzID, int roomNumber, LocalTime anfang, LocalTime ende,
                                            LocalDate datum, String benutzer){
        raum = roomService.getRoomByRoomNumber(roomNumber);
        Arbeitsplatz arbeitsplatz = getArbeitsplatz(platzID);
        List<Buchung> buchungen = arbeitsplatz.getBuchungen();
        boolean keineBuchungenImZeitraum = buchungen.stream().filter(e -> e.getLocalDate().isEqual(datum))
                .filter(e -> e.getAnfang().isBefore(anfang) && e.getEnde().isAfter(anfang) ||
                        e.getAnfang().isAfter(anfang)&& e.getEnde().isAfter(anfang) ||
                        e.getAnfang().equals(anfang) || e.getEnde().equals(anfang)||
                        e.getAnfang().equals(ende) || e.getEnde().equals(ende))
                .filter(e -> e.getAnfang().isBefore(ende) && e.getEnde().isAfter(ende)||
                        e.getAnfang().isBefore(ende) && e.getEnde().isBefore(ende)||
                        e.getAnfang().equals(ende) || e.getEnde().equals(ende)||
                        e.getAnfang().equals(anfang) || e.getEnde().equals(anfang))
                .toList().isEmpty();

        if(!keineBuchungenImZeitraum){
            return false;
        }
        arbeitsplatz.addBuchungen(new Buchung(buchungsID,datum,anfang,ende,benutzer));
        buchungsID++;
        return true;
    }
}