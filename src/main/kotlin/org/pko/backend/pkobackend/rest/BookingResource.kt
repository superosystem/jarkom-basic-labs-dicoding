//package org.pko.backend.pkobackend.rest
//
//import io.swagger.v3.oas.annotations.responses.ApiResponse
//import jakarta.validation.Valid
//import java.lang.Void
//import java.util.UUID
//import org.pko.backend.pkobackend.model.BookingDTO
//import org.pko.backend.pkobackend.service.BookingService
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
//    value = ["/api/bookings"],
//    produces = [MediaType.APPLICATION_JSON_VALUE]
//)
//class BookingResource(
//    private val bookingService: BookingService
//) {
//
//    @GetMapping
//    fun getAllBookings(): ResponseEntity<List<BookingDTO>> =
//            ResponseEntity.ok(bookingService.findAll())
//
//    @GetMapping("/{id}")
//    fun getBooking(@PathVariable(name = "id") id: UUID): ResponseEntity<BookingDTO> =
//            ResponseEntity.ok(bookingService.get(id))
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    fun createBooking(@RequestBody @Valid bookingDTO: BookingDTO): ResponseEntity<UUID> {
//        val createdId = bookingService.create(bookingDTO)
//        return ResponseEntity(createdId, HttpStatus.CREATED)
//    }
//
//    @PutMapping("/{id}")
//    fun updateBooking(@PathVariable(name = "id") id: UUID, @RequestBody @Valid
//            bookingDTO: BookingDTO): ResponseEntity<UUID> {
//        bookingService.update(id, bookingDTO)
//        return ResponseEntity.ok(id)
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiResponse(responseCode = "204")
//    fun deleteBooking(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
//        bookingService.delete(id)
//        return ResponseEntity.noContent().build()
//    }
//
//}
