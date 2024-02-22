package RoomMate.service.api;

import RoomMate.domain.model.api.KeymasterRaum;
import RoomMate.domain.model.api.KeymasterSchluessel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {


    KeymasterRaumRepository keymasterRepository;
    KeymasterSchluesselRepository keymasterSchluesselRepository;


    private static record Key(String id, String owner){}
    private static record Room(String id, String raum){}

    public EventService(KeymasterRaumRepository keymasterRepository, KeymasterSchluesselRepository keymasterSchluesselRepository) {
        this.keymasterSchluesselRepository=keymasterSchluesselRepository;
        this.keymasterRepository=keymasterRepository;

    }

    @Scheduled(fixedDelay = 10000)
    public void updateData(){
        System.out.println("updating");

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
        System.out.println(rooms);
        System.out.println(keys);



        for(Key key : keys){
            if(keymasterSchluesselRepository.findByName(key.owner).isEmpty()){
                KeymasterSchluessel save = keymasterSchluesselRepository.save(new KeymasterSchluessel(key.id, key.owner));
                System.out.println(save);
            }else if(!keymasterSchluesselRepository.findByName(key.owner).get().uuid().equals(key.id)){
                System.out.println("Schlüssel aktualisiert");
                KeymasterSchluessel keymasterSchluessel = keymasterSchluesselRepository.findByName(key.owner).get();
                KeymasterSchluessel save = keymasterSchluesselRepository.save(new KeymasterSchluessel(keymasterSchluessel.id(), key.id, keymasterSchluessel.owner()));
                System.out.println(save);
            }
        }
        for(Room room : rooms){
            if(keymasterRepository.findByRoom(room.raum).isEmpty()) {
                KeymasterRaum save = keymasterRepository.save(new KeymasterRaum(room.id, room.raum));
                System.out.println("gespeicherter raum"+save);
            }else if(!keymasterRepository.findByRoom(room.raum).get().uuid().equals(room.id)){
                KeymasterRaum keymasterRaum = keymasterRepository.findByRoom(room.raum).get();
                keymasterRepository.save(new KeymasterRaum(keymasterRaum.id(),room.id(), keymasterRaum.room()));
            }

        }

    }
}