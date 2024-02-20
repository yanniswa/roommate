package RoomMate.web;

import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.service.AdminService;
import RoomMate.service.BuchungsService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@Secured("ROLE_ADMIN")
public class AdminWebController {

    private final BuchungsService buchungsService;

    private final AdminService adminService;

    public AdminWebController(BuchungsService buchungsService, AdminService adminService) {
        this.buchungsService = buchungsService;
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String index(Model model){
        model.addAttribute("arbeitsplaetze",buchungsService.alleArbeitsplaetze());
        model.addAttribute("alleBuchungen",buchungsService.alleBuchungen());
        return "adminFunktionen";
    }

    @PostMapping("/admin")
    public String platzhinzufuegen(int raumnummer, String ausstattung1){
        Set<String> ausstattung = Set.of(ausstattung1);
        adminService.addArbeitsplatz(raumnummer, ausstattung);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{arbeitsplatzid}")
    public String ausstattunghinzufuegen(){
        return  "ausstattunghinzufuegen";
    }

    @PostMapping("/admin/{arbeitsplatzid}")
    public String ausstattunghinzugefuegt(@PathVariable Integer arbeitsplatzid, String ausstattung){
        adminService.addAusstattung(arbeitsplatzid, ausstattung);
        return "redirect:/admin";
    }
}
