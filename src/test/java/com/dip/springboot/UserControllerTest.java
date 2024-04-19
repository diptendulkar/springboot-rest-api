package com.dip.springboot;

import com.dip.springboot.dto.UserDto;
import com.dip.springboot.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUserShouldReturnSavedUser() throws Exception {
        UserDto user = new UserDto(); // Assume UserDto has appropriate fields. Initialize them as necessary.
      user.setEmail("aa@aa.com");
      user.setFirstName("aaa");
      user.setLastName("adas");

        UserDto savedUser = new UserDto(); // Initialize with expected saved user details.
        savedUser.setEmail("aa@aa.com");
        savedUser.setFirstName("aaa");
        savedUser.setLastName("adas");

        given(userService.createUser(any(UserDto.class))).willReturn(savedUser);

        mockMvc.perform(post("http://localhost:8080/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedUser.getId())); // Assuming UserDto has an 'id' field.
    }

    @Test
    void createUserWithInvalidDataShouldReturnBadRequest() throws Exception {
        UserDto invalidUser = new UserDto(); // Construct an invalid UserDto.

        // No need to mock userService since validation should fail before hitting the service layer.

        mockMvc.perform(post("http://localhost:8080/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest());
        // Optionally, you can assert more details about the response here.
    }
}
