package org.pko.backend.pkobackend.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import java.util.UUID
import org.pko.backend.pkobackend.model.KtpDTO
import org.pko.backend.pkobackend.service.KtpService
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
    value = ["/api/ktps"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class KtpResource(
    private val ktpService: KtpService
) {

    @GetMapping
    fun getAllKtps(): ResponseEntity<List<KtpDTO>> = ResponseEntity.ok(ktpService.findAll())

    @GetMapping("/{id}")
    fun getKtp(@PathVariable(name = "id") id: UUID): ResponseEntity<KtpDTO> =
            ResponseEntity.ok(ktpService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createKtp(@RequestBody @Valid ktpDTO: KtpDTO): ResponseEntity<UUID> {
        val createdId = ktpService.create(ktpDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateKtp(@PathVariable(name = "id") id: UUID, @RequestBody @Valid ktpDTO: KtpDTO):
            ResponseEntity<UUID> {
        ktpService.update(id, ktpDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteKtp(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
        ktpService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
