//package org.pko.backend.pkobackend.rest
//
//import io.swagger.v3.oas.annotations.responses.ApiResponse
//import jakarta.validation.Valid
//import java.lang.Void
//import java.util.UUID
//import org.pko.backend.pkobackend.model.FlightDTO
//import org.pko.backend.pkobackend.service.FlightService
//import org.springframework.http.HttpStatus
//import org.springframework.http.MediaType
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.DeleteMapping
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.PutMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//
//@RestController
//@RequestMapping(
//    value = ["/api/flights"],
//    produces = [MediaType.APPLICATION_JSON_VALUE]
//)
//class FlightResource(
//    private val flightService: FlightService
//) {
//
//    @GetMapping
//    fun getAllFlights(): ResponseEntity<List<FlightDTO>> =
//            ResponseEntity.ok(flightService.findAll())
//
//    @GetMapping("/{id}")
//    fun getFlight(@PathVariable(name = "id") id: UUID): ResponseEntity<FlightDTO> =
//            ResponseEntity.ok(flightService.get(id))
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    fun createFlight(@RequestBody @Valid flightDTO: FlightDTO): ResponseEntity<UUID> {
//        val createdId = flightService.create(flightDTO)
//        return ResponseEntity(createdId, HttpStatus.CREATED)
//    }
//
//    @PutMapping("/{id}")
//    fun updateFlight(@PathVariable(name = "id") id: UUID, @RequestBody @Valid flightDTO: FlightDTO):
//            ResponseEntity<UUID> {
//        flightService.update(id, flightDTO)
//        return ResponseEntity.ok(id)
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiResponse(responseCode = "204")
//    fun deleteFlight(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
//        flightService.delete(id)
//        return ResponseEntity.noContent().build()
//    }
//
//}
