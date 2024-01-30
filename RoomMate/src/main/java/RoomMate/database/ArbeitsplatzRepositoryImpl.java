package RoomMate.database;

import RoomMate.domain.model.Arbeitsplatz;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArbeitsplatzRepositoryImpl implements RoomMate.service.ArbeitsplatzRepository {
    List<Arbeitsplatz> arbeitsplaetze =  List.of(new Arbeitsplatz(List.of("USB","Computer"),1,25),new Arbeitsplatz(List.of("Steckdose","Computer"),2,25),
    new Arbeitsplatz(List.of("USB","Computer"),3,24),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor"),4,24),new Arbeitsplatz(List.of("Steckdose","Computer","Monitor","LanKabel"),5,24));

    public List<Arbeitsplatz> getArbeitsplaetze(){
        return arbeitsplaetze;
    }

    public Optional<Arbeitsplatz> getArbeitsplatzByID(int id){
        return arbeitsplaetze.stream().filter(e->e.getId()==id).findFirst();
    }
}
