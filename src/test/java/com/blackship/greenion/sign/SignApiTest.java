package com.blackship.greenion.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void signUp_callsSignUpInSignService() throws Exception {
        SignUser givenSignUser = new SignUser("email@email.com");

        mockMvc.perform(post("/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenSignUser)));

        assertThat(signService.signUp_argumentSignUser).isEqualTo(givenSignUser);
    }

    @Test
    void signUp_returnsSuccessSignUpResponse() throws Exception {
        SignUser givenSignUser = new SignUser("email@email.com");

        signService.signUp_returnValue = true;

        SignUpResponse expectedSignUpResponse = SignUpResponse.of(
                true,
                givenSignUser.getEmail());

        mockMvc.perform(post("/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenSignUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSignUpResponse)));
    }

    @Test
    void signUp_returnsFailSignUpResponse() throws Exception {
        SignUser givenSignUser = new SignUser("email@email.com");

        signService.signUp_returnValue = false;

        SignUpResponse expectedSignUpResponse = SignUpResponse.of(
                false,
                givenSignUser.getEmail());

        mockMvc.perform(post("/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenSignUser)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSignUpResponse)));
    }
}
