package com.example.roommate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Die Route /platzsuche ist vorhanden")
    void test_01() throws Exception{
        mvc.perform(get("/platzsuche"))
                .andExpect(status().isOk());
    }
}
