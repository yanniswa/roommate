package RoomMate.domain.model;

import RoomMate.annotations.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Value
public class Buchung {

    private final int id;
    private final LocalDate localDate;
    private final LocalTime anfang;
    private final LocalTime ende;
    private final String benutzer;

    private final Integer arbeitsplatzid;

    public Buchung(LocalDate localDate, LocalTime anfang, LocalTime ende, String benutzer, int id,int arbeitsPlatzId) {
        this.id = id;
        this.localDate = localDate;
        this.anfang = anfang;
        this.ende = ende;
        this.benutzer = benutzer;
        this.arbeitsplatzid = arbeitsPlatzId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buchung buchung = (Buchung) o;
        return id == buchung.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getArbeitsplatzid() {
        return arbeitsplatzid;
    }
}
