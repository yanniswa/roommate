package RoomMate.web;

import RoomMate.service.BuchungsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

    private final BuchungsService buchungsService;

    public AdminWebController(BuchungsService buchungsService) {
        this.buchungsService = buchungsService;
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public String index(Model model){
        model.addAttribute("buchungenList", buchungsService.alleBuchungen());
        return "adminFunktionen";
    }
}
