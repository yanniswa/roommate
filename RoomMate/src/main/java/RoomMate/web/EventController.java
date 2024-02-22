package RoomMate.web;

import RoomMate.domain.model.Benutzer;
import RoomMate.domain.model.Buchung;
import RoomMate.domain.model.api.KeymasterRaum;
import RoomMate.domain.model.api.KeymasterSchluessel;
import RoomMate.service.BuchungsService;
import RoomMate.service.api.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    public EventController(BuchungsService buchungsService, EventService eventService) {
        this.buchungsService = buchungsService;
        this.eventService = eventService;
    }

    private static record Key(String id, String owner){}
    private static record Room(String id, String raum){}
    private BuchungsService buchungsService;
    private EventService eventService;

//Es wird die Annahme getroffen, dass die Raumbezeichnung in RoomMate nur die Raumnummer als int ist
//Der Schlüssel besteht aus dem benutzername aus GitHub
    @GetMapping("/api/access")
    public List<Access> apiAcces(){
        System.out.println("Zugriff erhalten");
        List<KeymasterRaum> raume = eventService.alleKeymasterRaume();
        List<KeymasterSchluessel> schluessel = eventService.alleKeymasterSchluessel();
        List<Buchung> buchungen = buchungsService.alleBuchungen();
        List<Access> access = new ArrayList<>();
        System.out.println("5".equals(String.valueOf(5)));
        for (Buchung buchung : buchungen){
            String benutzer = buchung.getBenutzer().getBenutzername();
            int arbeitsplatzid = buchung.getArbeitsplatzid();
            int raumnummer = buchungsService.getArbeitsplatz(arbeitsplatzid).getRaumnummer();
            Optional<KeymasterSchluessel> optionalKeymasterSchluessel = schluessel.stream().filter(e -> e.owner().equals(benutzer)).findFirst();
            Optional<KeymasterRaum> optionalKeymasterRaum = raume.stream().filter(e -> e.room().equals(String.valueOf(raumnummer))).findFirst();
            if(optionalKeymasterRaum.isPresent()&&optionalKeymasterSchluessel.isPresent()){
                access.add(new Access(optionalKeymasterSchluessel.get().uuid(),optionalKeymasterRaum.get().uuid()));
            }

        }
        System.out.println(access);
        return access;
    }

}
