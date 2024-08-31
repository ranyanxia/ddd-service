package io.yanxia.ddd_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EchoController.class)
public class EchoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEcho() throws Exception {
        var expected = "Hello World from localhost";
        mockMvc.perform(get("/echo"))
            .andExpect(status().isOk())
            .andExpect(content().string(expected));
    }
}
