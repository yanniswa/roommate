package RoomMate.database;

import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Buchung;
import RoomMate.service.ArbeitsplatzRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.anyOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DataJdbcTest
class ArbeitsplatzRepositoryImplTest {
    @Autowired
    SpringDataArbeitsplatzRepository dataRepository;

    ArbeitsplatzRepository repository;

    @BeforeEach
    void setup(){repository = new ArbeitsplatzRepositoryImpl(dataRepository);}

    @Test
    @DisplayName("Wenn es mehrere Arbeitsplätze gibt werden mehrere gefunden")
    @Sql("findall.sql")
    void test_1() {
        List<Arbeitsplatz> all = repository.getArbeitsplaetze();
        assertThat(all).hasSize(3);
    }

    @Test
    @DisplayName("Wenn es keinen Arbeitsplatz gibt wird keiner zurückgegeben")
    void test_2() {
        List<Arbeitsplatz> all = repository.getArbeitsplaetze();
        assertThat(all).isEmpty();
    }

    @Test
    @DisplayName("FindById gibt richtigen Arbeitsplatz zurück")
    @Sql("findall.sql")
    void test_3() {
        Optional<Arbeitsplatz> arbeitsplatzByID = repository.getArbeitsplatzByID(1);


        assertThat(arbeitsplatzByID).isNotEmpty();
        assertThat(arbeitsplatzByID.get()).isEqualTo(new Arbeitsplatz(Set.of("Stuhl", "Tisch", "Lampe"),1,25));
    }

    @Test
    @DisplayName("Alle Buchungen werden korrekt zurückgegeben")
    @Sql("alleBuchungen.sql")
    void test_4() {
        List<Buchung> kontrollBuchung = List.of(new Buchung(LocalDate.of(2024, 2, 19), LocalTime.of(8, 0), LocalTime.of(12, 0), "Elon",1),new Buchung(LocalDate.of(2024, 2, 21), LocalTime.of(9, 0), LocalTime.of(13, 0), "Elon",2),
        new Buchung(LocalDate.of(2024, 2, 20), LocalTime.of(8, 30), LocalTime.of(12, 30), "Elon",3),new Buchung(LocalDate.of(2024, 2, 22), LocalTime.of(8, 0), LocalTime.of(12, 0), "Elon",4),
                new Buchung(LocalDate.of(2024, 2, 23), LocalTime.of(9, 30), LocalTime.of(13, 30), "Elon",5));

        List<Buchung> buchungen = repository.getBuchungen();
        assertThat(buchungen).hasSize(5).containsExactlyInAnyOrderElementsOf(kontrollBuchung);
    }


}