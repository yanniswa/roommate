package RoomMate.domain.model;

import RoomMate.annotations.Value;

import java.time.LocalDate;
import java.time.LocalTime;
@Value
public class Buchung {

    int id=-1;
    private final LocalDate localDate;
    private final LocalTime anfang;
    private final LocalTime ende;
    private final String benutzer;

    public Buchung(LocalDate localDateTime, LocalTime anfang, LocalTime ende, String benutzer) {
        this.id = id+1;
        this.localDate = localDateTime;
        this.anfang = anfang;
        this.ende = ende;
        this.benutzer = benutzer;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getAnfang() {
        return anfang;
    }

    public LocalTime getEnde() {
        return ende;
    }

    public int getId() {
        return id;
    }

    public String getBenutzer() {
        return benutzer;
    }
}
