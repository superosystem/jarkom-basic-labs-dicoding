package org.pko.backend.pkobackend.rest

import com.ninjasquad.springmockk.MockkBean
import io.mockk.justRun
import org.junit.jupiter.api.Test
import org.pko.backend.pkobackend.model.user.RegisterRequest
import org.pko.backend.pkobackend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(AuthResource::class)
class AuthResourceTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var service: UserService

    val registerPayload = RegisterRequest("John Doe", "john@example.com", "password", "")

    @Test
    fun testSignUp_SuccessfulRegistration() {
        justRun { service.signUp(registerPayload) }

        // Prepare test data
        val requestPayload = """
            {
                "name": "John Doe",
                "email": "john@example.com",
                "password": "password",
                "avatar": "avatar.jpg"
            }
        """.trimIndent()

        // Perform the HTTP POST request
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestPayload)
        )
                // Verify the response
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Registration successfully"))
    }
}