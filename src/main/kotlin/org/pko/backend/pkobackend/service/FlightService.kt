package org.pko.backend.pkobackend.service

import jakarta.transaction.Transactional
import java.util.UUID
import org.pko.backend.pkobackend.domain.Flight
import org.pko.backend.pkobackend.model.FlightDTO
import org.pko.backend.pkobackend.repos.AirlineRepository
import org.pko.backend.pkobackend.repos.BookingRepository
import org.pko.backend.pkobackend.repos.FlightRepository
import org.pko.backend.pkobackend.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class FlightService(
    private val flightRepository: FlightRepository,
    private val airlineRepository: AirlineRepository,
    private val bookingRepository: BookingRepository
) {

    fun findAll(): List<FlightDTO> {
        val flights = flightRepository.findAll(Sort.by("id"))
        return flights.stream()
                .map { flight -> mapToDTO(flight, FlightDTO()) }
                .toList()
    }

    fun `get`(id: UUID): FlightDTO = flightRepository.findById(id)
            .map { flight -> mapToDTO(flight, FlightDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(flightDTO: FlightDTO): UUID {
        val flight = Flight()
        mapToEntity(flightDTO, flight)
        return flightRepository.save(flight).id!!
    }

    fun update(id: UUID, flightDTO: FlightDTO) {
        val flight = flightRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(flightDTO, flight)
        flightRepository.save(flight)
    }

    fun delete(id: UUID) {
        flightRepository.deleteById(id)
    }

    private fun mapToDTO(flight: Flight, flightDTO: FlightDTO): FlightDTO {
        flightDTO.id = flight.id
        flightDTO.departTime = flight.departTime
        flightDTO.arrivalTime = flight.arrivalTime
        flightDTO.departure = flight.departure
        flightDTO.destination = flight.destination
        flightDTO.price = flight.price
        flightDTO.airlineId = flight.airlineId?.id
        flightDTO.bookings = flight.bookings?.stream()
                ?.map { booking -> booking.id!! }
                ?.toList()
        return flightDTO
    }

    private fun mapToEntity(flightDTO: FlightDTO, flight: Flight): Flight {
        flight.departTime = flightDTO.departTime
        flight.arrivalTime = flightDTO.arrivalTime
        flight.departure = flightDTO.departure
        flight.destination = flightDTO.destination
        flight.price = flightDTO.price
        val airlineId = if (flightDTO.airlineId == null) null else
                airlineRepository.findById(flightDTO.airlineId!!)
                .orElseThrow { NotFoundException("airlineId not found") }
        flight.airlineId = airlineId
        val bookings = bookingRepository.findAllById(flightDTO.bookings ?: emptyList())
        if (bookings.size != (if (flightDTO.bookings == null) 0 else flightDTO.bookings!!.size)) {
            throw NotFoundException("one of bookings not found")
        }
        flight.bookings = bookings.toMutableSet()
        return flight
    }

}
