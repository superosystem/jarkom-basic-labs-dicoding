package org.pko.backend.pkobackend.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import java.util.UUID
import org.pko.backend.pkobackend.model.AirlineDTO
import org.pko.backend.pkobackend.service.AirlineService
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
    value = ["/api/airlines"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AirlineResource(
    private val airlineService: AirlineService
) {

    @GetMapping
    fun getAllAirlines(): ResponseEntity<List<AirlineDTO>> =
            ResponseEntity.ok(airlineService.findAll())

    @GetMapping("/{id}")
    fun getAirline(@PathVariable(name = "id") id: UUID): ResponseEntity<AirlineDTO> =
            ResponseEntity.ok(airlineService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createAirline(@RequestBody @Valid airlineDTO: AirlineDTO): ResponseEntity<UUID> {
        val createdId = airlineService.create(airlineDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateAirline(@PathVariable(name = "id") id: UUID, @RequestBody @Valid
            airlineDTO: AirlineDTO): ResponseEntity<UUID> {
        airlineService.update(id, airlineDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteAirline(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
        airlineService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
