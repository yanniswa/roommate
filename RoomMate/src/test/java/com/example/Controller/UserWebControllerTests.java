package com.example.Controller;

import com.example.Controller.Helper.WithMockOAuth2User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserWebControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Get Request auf / funktioniert")
    @WithMockOAuth2User()
    void test_01() throws Exception{
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
