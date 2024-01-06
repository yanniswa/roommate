package com.example.Controller;

import com.example.Controller.Helper.WithMockOAuth2User;
import com.example.Controller.domain.applicationservice.BuchungsService;
import com.example.Controller.domain.applicationservice.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class UserWebControllerTests {

    @Autowired
    MockMvc mvc;
    @MockBean
    RoomService roomService;
    @MockBean
    BuchungsService buchungsService;

    @Test
    @DisplayName("Get Request auf /user funktioniert")
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
                .andExpect(view().name("user"));
    }



}
