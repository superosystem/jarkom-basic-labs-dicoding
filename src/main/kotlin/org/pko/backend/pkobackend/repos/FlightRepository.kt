package org.pko.backend.pkobackend.repos

import java.util.UUID
import org.pko.backend.pkobackend.domain.Booking
import org.pko.backend.pkobackend.domain.Flight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, UUID> {

    fun findAllByBookings(booking: Booking): List<Flight>

}
