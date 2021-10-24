package com.blackship.greenion.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        UserApi userApi = new UserApi();
        mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();
    }

    @Test
    void getUsers_isOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk());
    }

}