package RoomMate.web;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdminWebController.class)
public class AdminWebControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Get Request auf /admin funktioniert")
    void test_1() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

}
