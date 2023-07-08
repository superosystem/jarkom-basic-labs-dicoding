package org.pko.backend.pkobackend.model.user

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.pko.backend.pkobackend.validator.TruePassword

data class RegisterRequest(
        @field:NotEmpty(message = "name is required")
        @field:Size(min = 2, max = 150, message = "name must be between 2 and 150 characters")
        @JsonProperty("name")
        var name: String,

        @field:NotEmpty(message = "email is required")
        @field:Size(min = 5, max = 100, message = "email must be between 5 and 100 characters")
        @field:Email(message = "invalid email format")
        var email: String,

        @field:NotEmpty(message = "password is required")
        @field:Size(min = 8, max = 100, message = "password must be between 8 and 100 characters")
        @field:TruePassword
        var password: String,

        var avatar: String?,
)