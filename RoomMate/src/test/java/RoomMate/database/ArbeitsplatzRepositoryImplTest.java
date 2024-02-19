package RoomMate.database;

import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.service.ArbeitsplatzRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
}