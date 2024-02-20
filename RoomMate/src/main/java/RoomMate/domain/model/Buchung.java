package RoomMate.domain.model;

import RoomMate.annotations.Value;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Value
public class Buchung {


    private final LocalDate localDate;
    private final LocalTime anfang;
    private final LocalTime ende;
    private final String benutzer;

    private final Integer arbeitsplatzid;
    @PersistenceCreator
    public Buchung(LocalDate localDate, LocalTime anfang, LocalTime ende, String benutzer,int arbeitsPlatzId) {
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


    public String getBenutzer() {
        return benutzer;
    }




    public int getArbeitsplatzid() {
        return arbeitsplatzid;
    }
}
