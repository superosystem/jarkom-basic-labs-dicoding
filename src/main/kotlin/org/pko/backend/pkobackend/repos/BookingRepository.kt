package org.pko.backend.pkobackend.repos

import java.util.UUID
import org.pko.backend.pkobackend.domain.Booking
import org.springframework.data.jpa.repository.JpaRepository


interface BookingRepository : JpaRepository<Booking, UUID> {

    fun existsByBookingCodeIgnoreCase(bookingCode: String?): Boolean

}
