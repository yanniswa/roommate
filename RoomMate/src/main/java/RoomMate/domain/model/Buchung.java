package RoomMate.domain.model;

import RoomMate.annotations.Value;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Value
public class Buchung {

    private UUID id;
    private final LocalDate localDate;
    private final LocalTime anfang;
    private final LocalTime ende;
    private final Benutzer benutzer;

    private final Integer arbeitsplatzid;

    public Buchung(LocalDate localDate, LocalTime anfang, LocalTime ende, Benutzer benutzer,int arbeitsPlatzId) {
        this.localDate = localDate;
        this.anfang = anfang;
        this.ende = ende;
        this.benutzer = benutzer;
        this.arbeitsplatzid = arbeitsPlatzId;
        this.id = UUID.randomUUID();
    }

    public Buchung(LocalDate localDate, LocalTime anfang, LocalTime ende, Benutzer benutzer, Integer arbeitsplatzid, UUID id) {
        this.id = id;
        this.localDate = localDate;
        this.anfang = anfang;
        this.ende = ende;
        this.benutzer = benutzer;
        this.arbeitsplatzid = arbeitsplatzid;
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


    public Benutzer getBenutzer() {
        return benutzer;
    }

    public UUID getID(){
        return id;
    }




    public int getArbeitsplatzid() {
        return arbeitsplatzid;
    }
}
