package RoomMate.web;


import RoomMate.Helper.WithMockOAuth2User;
import RoomMate.config.MethodSecurityConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdminWebController.class)
@Import(MethodSecurityConfiguration.class)
public class AdminWebControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Get Request auf /admin funktioniert")
    @WithMockOAuth2User(login = "Elon",roles = {"USER"})
    void test_1() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Get Request auf /admin funktioniert mit admin rechten")
    @WithMockOAuth2User(login = "Elon",roles = {"ADMIN","USER"})
    void test_2() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

}
