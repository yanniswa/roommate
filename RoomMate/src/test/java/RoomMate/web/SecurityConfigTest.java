package RoomMate.web;

import RoomMate.Helper.WithMockOAuth2User;
import RoomMate.database.ArbeitsplatzRepositoryImpl;
import RoomMate.service.BuchungsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SecurityConfigTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BuchungsService buchungsService;



    @Test
    @DisplayName("Bei einem authentifizierten Aufruf wird status ok zurückgegeben")
    @WithMockOAuth2User(login = "Elon")
    void test_1() throws Exception {
        mvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

}