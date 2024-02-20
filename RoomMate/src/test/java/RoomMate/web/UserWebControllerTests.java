package RoomMate.web;


import RoomMate.domain.model.Arbeitsplatz;
import RoomMate.domain.model.Zeitslot;
import RoomMate.service.BuchungsService;
import RoomMate.Helper.WithMockOAuth2User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class UserWebControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    BuchungsService buchungsService;

    @Test
    @DisplayName("Get Request auf /key funktioniert")
    @WithMockOAuth2User()
    void test_01() throws Exception{
        mvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get Request gibt richtige HTML Datei zuück")
    @WithMockOAuth2User()
    void test_02() throws Exception {
        mvc.perform(get("/user"))
                .andExpect(view().name("key"));
    }

    @Test
    @DisplayName("Wenn eine Buchung nicht angenommen wird,werden Zeitslots angezeigt")
    @WithMockOAuth2User(login = "Elon")
    void test_03() throws Exception {
        List<Zeitslot> zeitslots = List.of(new Zeitslot(LocalTime.of(0, 0), LocalTime.of(19, 50)),
                new Zeitslot(LocalTime.of(22, 0), LocalTime.of(23, 50)));
        when(buchungsService.freieZeitslot(LocalDate.now().plusDays(1),1)).thenReturn(zeitslots);
        when(buchungsService.addBuchungToArbeitsplatz(anyInt(),any(LocalTime.class),any(LocalTime.class),any(LocalDate.class),anyString())).thenReturn(false);


        mvc.perform(post("/user/1")
                        .param("datum", LocalDate.now().plusDays(1).toString())
                        .param("anfang", LocalTime.of(20,0).toString())
                        .param("ende",LocalTime.of(22,0).toString())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("freieSlots",zeitslots))
                .andExpect(model().attribute("buchungsError","Es gibt schon eine Buchung in dem Zeitraum"));
    }

    @Test
    @DisplayName("Bei erfolgreicher Buchung wird man auf die Startseite zurückgeleitet")
    @WithMockOAuth2User
    void test_04() throws Exception {

        when(buchungsService.addBuchungToArbeitsplatz(anyInt(),any(LocalTime.class),any(LocalTime.class),any(LocalDate.class),anyString())).thenReturn(true);
        mvc.perform(post("/user/1")
                        .param("datum", LocalDate.now().plusDays(1).toString())
                        .param("anfang", LocalTime.of(20,0).toString())
                        .param("ende",LocalTime.of(22,0).toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

    }

    


}
