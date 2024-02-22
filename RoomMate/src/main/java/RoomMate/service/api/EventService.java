package RoomMate.service.api;

import RoomMate.domain.model.api.KeymasterRaum;
import RoomMate.domain.model.api.KeymasterSchluessel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {


    KeymasterRaumRepository keymasterRaumRepository;
    KeymasterSchluesselRepository keymasterSchluesselRepository;


    private static record Key(String id, String owner){}
    private static record Room(String id, String raum){}

    public EventService(KeymasterRaumRepository keymasterRepository, KeymasterSchluesselRepository keymasterSchluesselRepository) {
        this.keymasterSchluesselRepository=keymasterSchluesselRepository;
        this.keymasterRaumRepository =keymasterRepository;

    }

    @Scheduled(fixedDelay = 20000)
    public void updateData(){
        System.out.println("updating");

        RestTemplate restTemplate = new RestTemplate();
        List<Key> keys = fetchSchluessel();
        List<Room> rooms = fetchRaum();




        for(Key key : keys){
            if(keymasterSchluesselRepository.findByName(key.owner).isEmpty()){
                KeymasterSchluessel save = keymasterSchluesselRepository.save(new KeymasterSchluessel(key.id, key.owner));

            }else if(!keymasterSchluesselRepository.findByName(key.owner).get().uuid().equals(key.id)){
                System.out.println("Schlüssel aktualisiert");
                KeymasterSchluessel keymasterSchluessel = keymasterSchluesselRepository.findByName(key.owner).get();
                KeymasterSchluessel save = keymasterSchluesselRepository.save(new KeymasterSchluessel(keymasterSchluessel.id(), key.id, keymasterSchluessel.owner()));

            }
        }
        for(Room room : rooms){
            if(keymasterRaumRepository.findByRoom(room.raum).isEmpty()) {
                KeymasterRaum save = keymasterRaumRepository.save(new KeymasterRaum(room.id, room.raum));

            }else if(!keymasterRaumRepository.findByRoom(room.raum).get().uuid().equals(room.id)){
                KeymasterRaum keymasterRaum = keymasterRaumRepository.findByRoom(room.raum).get();
                keymasterRaumRepository.save(new KeymasterRaum(keymasterRaum.id(),room.id(), keymasterRaum.room()));
            }

        }

    }
    private List<Key> fetchSchluessel(){
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:3000").build();
        List<Key> keys = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/key")
                        .build())
                .retrieve()
                .bodyToFlux(Key.class)
                .collectList()
                .block(Duration.ofSeconds(3));
        return keys;
    }
    private List<Room> fetchRaum(){
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:3000").build();
        List<Room> keys = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/room")
                        .build())
                .retrieve()
                .bodyToFlux(Room.class)
                .collectList()
                .block(Duration.ofSeconds(3));
        return keys;
    }
    public List<KeymasterRaum> alleKeymasterRaume(){
        return keymasterRaumRepository.findAll();
    }
    public List<KeymasterSchluessel> alleKeymasterSchluessel(){
        return keymasterSchluesselRepository.findAll();
    }
}