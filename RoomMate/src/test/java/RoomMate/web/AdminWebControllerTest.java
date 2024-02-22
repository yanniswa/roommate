package RoomMate.web;


import RoomMate.Helper.WithMockOAuth2User;
import RoomMate.config.MethodSecurityConfiguration;
import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Benutzer;
import RoomMate.domain.model.Buchung;
import RoomMate.service.AdminService;
import RoomMate.service.BuchungsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AdminWebController.class)
@Import(MethodSecurityConfiguration.class)
public class AdminWebControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BuchungsService buchungsService;
    @MockBean
    AdminService adminService;
    List<Arbeitsplatz> arbeitsplaetze =  List.of(new Arbeitsplatz(Set.of("USB","Computer"),1,25),new Arbeitsplatz(Set.of("Steckdose","Computer"),2,25),
            new Arbeitsplatz(Set.of("USB","Computer"),3,24),new Arbeitsplatz(Set.of("Steckdose","Computer","Monitor"),4,24),new Arbeitsplatz(Set.of("Steckdose","Computer","Monitor","LanKabel"),5,24));
    List<Buchung> buchungen = List.of(new Buchung(LocalDate.now(), LocalTime.now(),LocalTime.now().plusHours(2),new Benutzer("Elon"),3));



    @Test
    @DisplayName("Admin Seite fügt die Arbeitsplätze hinzu")
    @WithMockOAuth2User(login = "Elon",roles = {"ADMIN","USER"})
    void test_3() throws Exception {
        when(buchungsService.alleArbeitsplaetze()).thenReturn(arbeitsplaetze);
        mockMvc.perform(get("/admin"))
                .andExpect(model().attribute("arbeitsplaetze", arbeitsplaetze))
                .andExpect(content().string(containsString("Steckdose")));
    }
    @Test
    @DisplayName("Admin Seite fügt die Buchungen hinzu")
    @WithMockOAuth2User(login = "Elon",roles = {"ADMIN","USER"})
    void test_4() throws Exception {
        when(buchungsService.alleBuchungen()).thenReturn(buchungen);
        mockMvc.perform(get("/admin"))
                .andExpect(model().attribute("alleBuchungen",buchungen ));
    }




}
