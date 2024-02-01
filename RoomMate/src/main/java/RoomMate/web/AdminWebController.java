package RoomMate.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminWebController {

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String index(){
        return "adminFunktionen";
    }
}
