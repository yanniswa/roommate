package RoomMate.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public record Buchung(@Id UUID Id, LocalDate localDate, LocalTime anfang, LocalTime ende, Benutzer benutzer, Integer arbeitsplatz) {
}