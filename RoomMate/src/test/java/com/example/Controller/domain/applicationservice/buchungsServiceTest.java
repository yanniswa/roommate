package com.example.Controller.domain.applicationservice;

import com.example.Controller.database.ArbeitsplatzRepository;
import com.example.Controller.domain.model.Arbeitsplatz;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.LocalTime;

class buchungsServiceTest {

    ArbeitsplatzRepository rep = new ArbeitsplatzRepository();

    @Test
    @DisplayName("Buchung wird hinzugefügt, wenn Zeitslot frei ist ")
    void test_1() {
        BuchungsService buchungsService = new BuchungsService(rep);
        boolean b = buchungsService.addBuchungToArbeitsplatz(1,LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        assertThat(b).isTrue();
    }

    @Test
    @DisplayName("Raum wird nicht hinzugefügt, wenn Zeitslot belegt ist ist ")
    void test_2() {
        BuchungsService buchungsService = new BuchungsService(rep);
        boolean b = buchungsService.addBuchungToArbeitsplatz(1,LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        boolean a = buchungsService.addBuchungToArbeitsplatz(1,LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        assertThat(a).isFalse();
    }
    @Test
    @DisplayName("Ungueltige Zeiteingabe")
    void test_3() {
        BuchungsService buchungsService = new BuchungsService(rep);
        boolean b = buchungsService.addBuchungToArbeitsplatz(1,LocalTime.of(0, 0)
                ,LocalTime.of(2, 0) , LocalDate.of(2004, 02, 11), "Test");
        assertThat(b).isFalse();
    }
}