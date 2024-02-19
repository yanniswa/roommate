package RoomMate.database;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public record Arbeitsplatz(@Id Integer Id, List<Buchung> buchungen, Room room, Set<String> ausstattung) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arbeitsplatz that = (Arbeitsplatz) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
