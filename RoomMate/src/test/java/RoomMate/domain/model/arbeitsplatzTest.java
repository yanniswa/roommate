package RoomMate.domain.model;


import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class arbeitsplatzTest {



    @Test
    @DisplayName("Buchung wird hinzugefügt, wenn Zeitslot frei ist ")
    void test_1() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);

        boolean b = arbeitsplatz.addBuchung(LocalTime.of(0, 0), LocalTime.of(2, 0), LocalDate.now().plusDays(1), "test");

        assertThat(b).isTrue();

    }

    @Test
    @DisplayName("Raum wird nicht hinzugefügt, wenn Zeitslot belegt ist ist ")
    void test_2() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);
        arbeitsplatz.addBuchung(LocalTime.of(0, 0)
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

    @Test
    @DisplayName("Wenn eine neue Buchung eine bereits vorhanden ummantelt wird diese nicht hinzugefügt")
    void test_6() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);
        arbeitsplatz.addBuchung(LocalTime.of(1,0),LocalTime.of(2,0),LocalDate.now(),"test");

        boolean b = arbeitsplatz.addBuchung(LocalTime.of(0, 0), LocalTime.of(3, 0), LocalDate.now(), "test");

        assertThat(b).isFalse();
    }

    @Test
    @DisplayName("Wenn keine Buchung vorhanden ist, werden richtige Zeitslots berechnet")
    void test_7() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);

        List<Zeitslot> zeitslots = arbeitsplatz.freieZeitslots(LocalDate.now());

        assertThat(zeitslots).containsExactly(new Zeitslot(LocalTime.of(0,0),LocalTime.of(23,50)));
    }

    @Test
    @DisplayName("Die Methode freiZeitslots wirft keine UnsupportedOperationException")
    void test_8() {
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);
        arbeitsplatz.addBuchung(LocalTime.of(1, 0), LocalTime.of(2, 0), LocalDate.now().plusDays(1), "test");

        assertDoesNotThrow(()-> {
            arbeitsplatz.freieZeitslots(LocalDate.now().plusDays(1));
        });

    }
    @Test
    @DisplayName("Wenn eine Buchung vorhanden ist, werden richtige Zeitslots berechnet")
    void test_9(){
        Arbeitsplatz arbeitsplatz = new Arbeitsplatz(null,1,25);
        arbeitsplatz.addBuchung(LocalTime.of(1, 0), LocalTime.of(2, 0), LocalDate.now().plusDays(1), "test");

        List<Zeitslot> zeitslots = arbeitsplatz.freieZeitslots(LocalDate.now().plusDays(1));

        assertThat(zeitslots).containsExactly(new Zeitslot(LocalTime.of(0,0),LocalTime.of(0,50)),
                new Zeitslot(LocalTime.of(2,0),LocalTime.of(23,50)));
    }
}