package org.pko.backend.pkobackend.repos

import org.pko.backend.pkobackend.domain.Booking
import org.pko.backend.pkobackend.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun existsByEmailIgnoreCase(email: String?): Boolean

    fun findAllByBookings(booking: Booking): List<User>

}
