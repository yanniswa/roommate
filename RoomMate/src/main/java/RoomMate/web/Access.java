package RoomMate.web;

import java.util.Objects;

public record Access(String key, String room) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Access access = (Access) o;
        return Objects.equals(key, access.key) && Objects.equals(room, access.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, room);
    }
}
