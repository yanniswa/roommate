package RoomMate.web;

import RoomMate.Helper.WithMockOAuth2User;
import RoomMate.config.MethodSecurityConfiguration;
import RoomMate.service.AdminService;
import RoomMate.service.BuchungsService;
import RoomMate.service.api.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest()
@Import(MethodSecurityConfiguration.class)
class SecurityConfigTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BuchungsService buchungsService;
    @MockBean
    AdminService adminService;
    @MockBean
    EventService eventService;



    @Test
    @DisplayName("Bei einem authentifizierten Aufruf wird status ok zurückgegeben")
    @WithMockOAuth2User(login = "Elon")
    void test_1() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Get Request auf /admin funktioniert")
    @WithMockOAuth2User(login = "Elon",roles = {"USER"})
    void test_2() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Get Request auf /admin funktioniert mit admin rechten")
    @WithMockOAuth2User(login = "Elon",roles = {"ADMIN","USER"})
    void test_3() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminFunktionen"));
    }

}