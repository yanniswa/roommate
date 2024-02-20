package RoomMate.web;

import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Buchung;
import RoomMate.service.BuchungsService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class EventController {
    public EventController(BuchungsService buchungsService) {
        this.buchungsService = buchungsService;
    }

    private static record User(String id, String owner){}
    private static record Room(String id, String raumnummer){}
    private BuchungsService buchungsService;


    @GetMapping("/api/access")
    public List<Access> apiAcces(){
        System.out.println("zugriff erhalten");
        List<Buchung> buchungen = buchungsService.alleBuchungen();
        RestTemplate restTemplate = new RestTemplate();
        List<User> user = new ArrayList<>(restTemplate.exchange(
                "http://localhost:3000/key",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody());
        List<Room> rooms = new ArrayList<>(restTemplate.exchange(
                "http://localhost:3000/room",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Room>>() {
                }).getBody());
        List<Access> result = new ArrayList<>();
        for(Buchung buchung : buchungen){
            Optional<User> userr = user.stream().filter(e -> e.owner.equals(buchung.getBenutzer())).findFirst();
            if(userr.isEmpty()){}
            else{
                int arbeitsplatzid = buchung.getArbeitsplatzid();
                Arbeitsplatz arbeitsplatz = buchungsService.getArbeitsplatz(arbeitsplatzid);
                Stream<Room> stream = rooms.stream().filter(e -> e.raumnummer.equals(arbeitsplatz.getRaumnummer()));
                Room room = stream.findFirst().get();
                result.add(new Access(userr.get().id, room.id));
            }

        }

        return result;
    }

}
