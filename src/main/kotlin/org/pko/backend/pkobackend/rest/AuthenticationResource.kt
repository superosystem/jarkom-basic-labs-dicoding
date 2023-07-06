package org.pko.backend.pkobackend.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import java.util.UUID
import org.pko.backend.pkobackend.model.AuthenticationDTO
import org.pko.backend.pkobackend.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/authentications"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AuthenticationResource(
    private val authenticationService: AuthenticationService
) {

    @GetMapping
    fun getAllAuthentications(): ResponseEntity<List<AuthenticationDTO>> =
            ResponseEntity.ok(authenticationService.findAll())

    @GetMapping("/{id}")
    fun getAuthentication(@PathVariable(name = "id") id: UUID): ResponseEntity<AuthenticationDTO> =
            ResponseEntity.ok(authenticationService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createAuthentication(@RequestBody @Valid authenticationDTO: AuthenticationDTO):
            ResponseEntity<UUID> {
        val createdId = authenticationService.create(authenticationDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateAuthentication(@PathVariable(name = "id") id: UUID, @RequestBody @Valid
            authenticationDTO: AuthenticationDTO): ResponseEntity<UUID> {
        authenticationService.update(id, authenticationDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteAuthentication(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
        authenticationService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
