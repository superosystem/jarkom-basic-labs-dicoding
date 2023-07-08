package org.pko.backend.pkobackend.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.pko.backend.pkobackend.model.MessageDTO
import org.pko.backend.pkobackend.model.user.RegisterRequest
import org.pko.backend.pkobackend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
        value = ["/api/v1/auth"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AuthResource(
        private val service: UserService

) {

    @PostMapping("/register")
    @ApiResponse(responseCode = "201")
    fun signUp(@Validated @RequestBody payload: RegisterRequest): ResponseEntity<MessageDTO> {
        service.signUp(payload)

        return ResponseEntity(MessageDTO("Registration successfully"), HttpStatus.CREATED)
    }
}