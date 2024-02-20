package RoomMate.web;

import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Buchung;
import RoomMate.service.BuchungsService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class EventController {
    public EventController(BuchungsService buchungsService) {
        this.buchungsService = buchungsService;
    }

    private static record Key(String id, String owner){}
    private static record Room(String id, String raum){}
    private BuchungsService buchungsService;


    @GetMapping("/api/access")
    public List<Access> apiAcces(){
        System.out.println("zugriff erhalten");
        List<Buchung> buchungen = buchungsService.alleBuchungen();
        RestTemplate restTemplate = new RestTemplate();
        List<Key> keys = new ArrayList<>(restTemplate.exchange(
                "http://localhost:3000/key",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Key>>() {
                }).getBody());
        List<Room> rooms = new ArrayList<>(restTemplate.exchange(
                "http://localhost:3000/room",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Room>>() {
                }).getBody());
        List<Access> result = new ArrayList<>();
        for(Buchung buchung : buchungen){
            Optional<Key> key = keys.stream().filter(e -> e.owner.equals(buchung.getBenutzer())).findFirst();
            if(key.isEmpty()){}
            else{
                int arbeitsplatzid = buchung.getArbeitsplatzid();
                Arbeitsplatz arbeitsplatz = buchungsService.getArbeitsplatz(arbeitsplatzid);
                System.out.println(arbeitsplatzid);
                Stream<Room> stream = rooms.stream().filter(e -> Integer.parseInt(e.raum)==arbeitsplatz.getRaumnummer());
                List<Room> list = stream.toList();
                System.out.println(list);
                Room room = list.getFirst();
                result.add(new Access(key.get().id(), room.id()));
            }

        }
        System.out.println(result.stream().collect(Collectors.toSet()));
        Set<Access> collect = result.stream().collect(Collectors.toSet());
        return collect.stream().toList();
    }
//Der neue Schlüssel hat die ID: 5b53a9b5-f95e-45a9-947d-94a50b99eefb
//Der neue Raum hat die ID: 86e7a9f7-3700-4893-b8be-1fed9a27d85d
}
