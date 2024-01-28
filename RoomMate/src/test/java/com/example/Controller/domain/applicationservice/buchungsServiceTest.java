package com.example.Controller.domain.applicationservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.LocalTime;

class buchungsServiceTest {

    RoomService roomService = new RoomService();

    @Test
    @DisplayName("Raum wird hinzugefügt, wenn Zeitslot frei ist ")
    void test_1() {
        BuchungsService buchungsService = new BuchungsService(roomService);
        boolean b = buchungsService.addBuchungToArbeitsplatz(1, 25, LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        assertThat(b).isTrue();
        assertThat(roomService.getRoomByRoomNumber(25).getArbeitsplaetze().get(0).getBuchungen()).hasSize(1);
    }

    @Test
    @DisplayName("Raum wird nicht hinzugefügt, wenn Zeitslot belegt ist ist ")
    void test_2() {
        BuchungsService buchungsService = new BuchungsService(roomService);
        boolean b = buchungsService.addBuchungToArbeitsplatz(1, 25, LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        boolean a = buchungsService.addBuchungToArbeitsplatz(1, 25, LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        assertThat(a).isFalse();
        assertThat(roomService.getRoomByRoomNumber(25).getArbeitsplaetze().get(0).getBuchungen()).hasSize(1);
    }
    @Test
    @DisplayName("Ungueltige Zeiteingabe")
    void test_3() {
        BuchungsService buchungsService = new BuchungsService(roomService);
        boolean b = buchungsService.addBuchungToArbeitsplatz(1, 25, LocalTime.of(0, 0)
                ,LocalTime.of(2, 0) , LocalDate.of(2004, 02, 11), "Test");
        assertThat(b).isFalse();
        assertThat(roomService.getRoomByRoomNumber(25).getArbeitsplaetze().get(0).getBuchungen()).hasSize(0);

    }
}