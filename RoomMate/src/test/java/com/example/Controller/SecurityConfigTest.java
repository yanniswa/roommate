package com.example.Controller;

import com.example.Controller.Helper.WithMockOAuth2User;
import com.example.Controller.domain.applicationservice.BuchungsService;
import com.example.Controller.domain.applicationservice.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SecurityConfigTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    RoomService roomService;

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