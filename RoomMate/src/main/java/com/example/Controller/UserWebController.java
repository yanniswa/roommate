package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class UserWebController {
    private final RoomService roomService;

    public UserWebController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/user/{roomNumber}")
    public String plaetzeUebersicht(@PathVariable Integer roomNumber,Model model){
        Optional<Room> raum = roomService.getAllRooms().stream()
                .filter(e -> e.getRoomnumber() == roomNumber)
                .findAny();
        if(raum.isPresent()){
            model.addAttribute("raum",raum.get());
        }
        model.addAttribute("arbeitsplaetze",raum.get().getArbeitsplaetze());
        return "platzUebersicht";
    }
    @GetMapping("/")
    public String index(Model model, RoomService roomService){
        model.addAttribute("raumList",roomService.getAllRooms());
        return "index";
    }

    @GetMapping("/user")
    public String userSeite(Model model,RoomService roomService){
        model.addAttribute("raumList",roomService.getAllRooms());
        return "user";
    }


}
