package org.pko.backend.pkobackend.service

import jakarta.transaction.Transactional
import java.util.UUID
import org.pko.backend.pkobackend.domain.Booking
import org.pko.backend.pkobackend.model.BookingDTO
import org.pko.backend.pkobackend.repos.BookingRepository
import org.pko.backend.pkobackend.repos.FlightRepository
import org.pko.backend.pkobackend.repos.UserRepository
import org.pko.backend.pkobackend.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class BookingService(
    private val bookingRepository: BookingRepository,
    private val userRepository: UserRepository,
    private val flightRepository: FlightRepository
) {

    fun findAll(): List<BookingDTO> {
        val bookings = bookingRepository.findAll(Sort.by("id"))
        return bookings.stream()
                .map { booking -> mapToDTO(booking, BookingDTO()) }
                .toList()
    }

    fun `get`(id: UUID): BookingDTO = bookingRepository.findById(id)
            .map { booking -> mapToDTO(booking, BookingDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(bookingDTO: BookingDTO): UUID {
        val booking = Booking()
        mapToEntity(bookingDTO, booking)
        return bookingRepository.save(booking).id!!
    }

    fun update(id: UUID, bookingDTO: BookingDTO) {
        val booking = bookingRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(bookingDTO, booking)
        bookingRepository.save(booking)
    }

    fun delete(id: UUID) {
        val booking = bookingRepository.findById(id)
                .orElseThrow { NotFoundException() }
        // remove many-to-many relations at owning side
        userRepository.findAllByBookings(booking)
                .forEach { user -> user.bookings!!.remove(booking) }
        flightRepository.findAllByBookings(booking)
                .forEach { flight -> flight.bookings!!.remove(booking) }
        bookingRepository.delete(booking)
    }

    private fun mapToDTO(booking: Booking, bookingDTO: BookingDTO): BookingDTO {
        bookingDTO.id = booking.id
        bookingDTO.status = booking.status
        bookingDTO.bookingCode = booking.bookingCode
        bookingDTO.passengerName = booking.passengerName
        bookingDTO.passengerTitle = booking.passengerTitle
        return bookingDTO
    }

    private fun mapToEntity(bookingDTO: BookingDTO, booking: Booking): Booking {
        booking.status = bookingDTO.status
        booking.bookingCode = bookingDTO.bookingCode
        booking.passengerName = bookingDTO.passengerName
        booking.passengerTitle = bookingDTO.passengerTitle
        return booking
    }

    fun bookingCodeExists(bookingCode: String?): Boolean =
            bookingRepository.existsByBookingCodeIgnoreCase(bookingCode)

}
