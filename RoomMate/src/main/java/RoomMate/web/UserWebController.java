package RoomMate.web;


import RoomMate.database.ArbeitsplatzRepositoryImpl;
import RoomMate.service.BuchungsService;
import RoomMate.domain.model.BuchungsForm;
import RoomMate.domain.model.Zeitslot;
import jakarta.validation.Valid;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserWebController {
    private final BuchungsService buchungsService;


    public UserWebController(BuchungsService arbeitsplatzService) {
        this.buchungsService = arbeitsplatzService;
    }



    @PostMapping("user/{arbeitsplatzId}")
    public String platzBuchen(@ModelAttribute("form") @Valid BuchungsForm buchungsForm,
                              BindingResult bindingResult,@PathVariable Integer arbeitsplatzId,
                              Model model,OAuth2AuthenticationToken auth){
        model.addAttribute("platzId",arbeitsplatzId);
        if(bindingResult.hasErrors()){
            return "buchvorgang";
        }
        if(buchungsForm.getAnfang().isAfter(buchungsForm.getEnde())){
            model.addAttribute("error","Anfangszeit muss vor Endzeit sein");
            return "buchvorgang";
        }
        String name = auth.getPrincipal().getAttribute("login");
        if(!buchungsService.addBuchungToArbeitsplatz(arbeitsplatzId,buchungsForm.getAnfang(),buchungsForm.getEnde(),buchungsForm.getDatum(),name)){
            model.addAttribute("buchungsError","Es gibt schon eine Buchung in dem Zeitraum");
            List<Zeitslot> zeitslots = buchungsService.freieZeitslot(buchungsForm.getDatum(), arbeitsplatzId);
            model.addAttribute("freieSlots",zeitslots);
            return "buchvorgang";
        }else model.addAttribute("success","Buchung war erfolgreich");
        return "redirect:/";
    }
    @GetMapping("/user/{arbeitsplatzId}")
    public String plaetzeUebersicht(@PathVariable Integer arbeitsplatzId,Model model){
        model.addAttribute("titel","Buchung vom Platz "+arbeitsplatzId);
        model.addAttribute("platzId",arbeitsplatzId);
        model.addAttribute("form",new BuchungsForm());
        return "buchvorgang";
    }
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("arbeitsplatzList",buchungsService.alleArbeitsplaetze());
        return "index";
    }

    @GetMapping("/user")
    public String userSeite(Model model){
        model.addAttribute("arbeitsplatzList",buchungsService.alleArbeitsplaetze());
        return "user";
    }


}
