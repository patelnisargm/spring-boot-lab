package com.nmp.springmvcdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringMvcDemoApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void getForm() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/form?id=123");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
				.andExpect(view().name("form"))
                .andExpect(model().attributeExists("grade"));
    }

    @Test
    public void getGrades() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/grades");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("grades"))
                .andExpect(model().attributeExists("grades"));
    }

    @Test
    public void handleSuccessfulSubmit() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/submitForm")
                .param("name", "John")
                .param("subject", "Java")
                .param("score", "C");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/grades"));
    }

    @Test
    public void handleUnsuccessfulSubmit() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/submitForm")
                .param("name", " ")
                .param("subject", " ")
                .param("score", " ");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("form"));
    }
}
