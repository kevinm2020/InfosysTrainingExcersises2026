package com.infosys.irs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.infosys.irs.model.User;
import com.infosys.irs.service.RegistrationService;

@WebMvcTest(controllers = RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock ONLY the service used by the controller
    @MockBean
    private RegistrationService registrationService;

    private User invalidUser;
    private User validUser;

    private final String command = "command";

    @BeforeEach
    void setUp() {

        // Invalid user (missing required fields → validation failure)
        invalidUser = new User();
        invalidUser.setUserId("u1");

        // Valid user
        validUser = new User();
        validUser.setUserId("u2");
        validUser.setPassword("pass");
        validUser.setName("Kevin");
        validUser.setCity("Chennai");
        validUser.setEmail("kevin@email.com");
        validUser.setPhoneNumber("1234567890");
    }

    @Test
    void testSubmitCustomerFailsValidation() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("userId", invalidUser.getUserId()))
            .andExpect(status().isOk())
            .andExpect(view().name("register"))
            .andExpect(model().attributeHasFieldErrors(command))
            .andDo(print());
    }

    @Test
    void testSubmitCustomerPassesValidation() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("userId", validUser.getUserId())
                .param("password", validUser.getPassword())
                .param("name", validUser.getName())
                .param("city", validUser.getCity())
                .param("email", validUser.getEmail())
                .param("phoneNumber", validUser.getPhoneNumber()))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasNoErrors(command))
            .andDo(print());
    }
}
