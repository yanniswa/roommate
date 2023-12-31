package com.example.Controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserWebController {
    private final RoomService roomService;

    public UserWebController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping("/user/{roomNumber}/buchung/{platzID}")
    public String platzBuchen(@ModelAttribute("form") @Valid BuchungsForm buchungsForm,
                              BindingResult bindingResult,
                              @PathVariable Integer platzID, Model model){
        model.addAttribute("platzId",platzID);
        if(bindingResult.hasErrors()){
            return "buchvorgang";
        }
        return "redirect:/";
    }
    @GetMapping("/user/{roomNumber}/buchung/{platzID}")
    public String indexBuchvorgang(Model model,@PathVariable Integer platzID){
        model.addAttribute("titel","Buchung vom Platz "+platzID);
        model.addAttribute("platzId",platzID);
        model.addAttribute("form",new BuchungsForm());
        return "buchvorgang";
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
