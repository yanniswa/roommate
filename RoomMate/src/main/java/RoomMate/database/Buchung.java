package RoomMate.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public record Buchung(LocalDate localDate, LocalTime anfang, LocalTime ende, String  benutzer, Integer arbeitsplatz) {
}