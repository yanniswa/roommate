package RoomMate.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;

class arbeitsplatzTest {



    @Test
    @DisplayName("Buchung wird hinzugefügt, wenn Zeitslot frei ist ")
    void test_1() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);

        boolean b = arbeitsplatz.addBuchung(LocalTime.of(0, 0), LocalTime.of(2, 0), LocalDate.now(), "test");

        assertThat(b).isTrue();

    }

    @Test
    @DisplayName("Raum wird nicht hinzugefügt, wenn Zeitslot belegt ist ist ")
    void test_2() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);
        boolean b = arbeitsplatz.addBuchung(LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        boolean a = arbeitsplatz.addBuchung(LocalTime.of(0, 0)
                , LocalTime.of(2, 0), LocalDate.now(), "Test");
        assertThat(a).isFalse();
    }
    @Test
    @DisplayName("Datum in der Vergangenheit wird nicht akzeptiert")
    void test_3() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);

        boolean b = arbeitsplatz.addBuchung(LocalTime.of(0, 0)
                ,LocalTime.of(2, 0) , LocalDate.of(2004, 02, 11), "Test");
        assertThat(b).isFalse();
    }

    @Test
    @DisplayName("Ungueltige Eingabe mit vergangener AnfangsUhrzeit werden nicht akzeptiert")
    void test_4() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);

        boolean b = arbeitsplatz.addBuchung(LocalTime.now().minusHours(1), LocalTime.MAX, LocalDate.now(), "Test");

        assertThat(b).isFalse();
    }

    @Test
    @DisplayName("Eingabe in denen die Anfangsuhrzeit nach der Enduhrzeit ist werden nicht akzeptiert")
    void test_5() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);

        boolean b = arbeitsplatz.addBuchung(LocalTime.now(), LocalTime.now().minusHours(1), LocalDate.now(), "test");

        assertThat(b).isFalse();
    }
}