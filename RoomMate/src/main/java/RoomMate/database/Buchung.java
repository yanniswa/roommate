package RoomMate.database;

import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public record Buchung(int id, LocalDate localDate, LocalTime anfang, LocalTime ende, String  benutzer,@Transient Integer arbeitsplatz) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buchung that = (Buchung) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}