package com.blackship.greenion.user.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SignApiTest {

    private MockMvc mockMvc;
    private SpySignService signService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        signService = new SpySignService();
        SignApi signApi = new SignApi(
                signService
        );
        mockMvc = MockMvcBuilders.standaloneSetup(signApi).build();
    }

    @Test
    void signUp_isOk() throws Exception {
        mockMvc.perform(post("/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void signUp_callsSignUpInSignService() throws Exception {
        SignUser givenSignUser = new SignUser("email@email.com");

        mockMvc.perform(post("/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenSignUser)))
                .andExpect(status().isOk());

        assertThat(signService.signUp_argumentSignUser).isEqualTo(givenSignUser);
    }
}
