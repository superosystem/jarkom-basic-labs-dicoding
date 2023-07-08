package org.pko.backend.pkobackend.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.ManyToMany
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class Booking : BaseDomain() {
    @Column(nullable = false, length = 10)
    var status: String = ""

    @Column(nullable = false, unique = true, length = 100)
    var bookingCode: String = ""

    @Column(nullable = false)
    var passengerName: String = ""

    @Column(nullable = false, length = 10)
    var passengerTitle: String = ""

    @ManyToMany(mappedBy = "bookings")
    var users: MutableSet<User>? = null

    @ManyToMany(mappedBy = "bookings")
    var flights: MutableSet<Flight>? = null
}