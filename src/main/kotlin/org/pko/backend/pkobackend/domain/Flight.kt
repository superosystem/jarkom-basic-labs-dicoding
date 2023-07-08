package org.pko.backend.pkobackend.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class Flight : BaseDomain() {
    @Column(nullable = false)
    var departTime: OffsetDateTime? = null

    @Column(nullable = false)
    var arrivalTime: OffsetDateTime? = null

    @Column(nullable = false, length = 50)
    var departure: String = ""

    @Column(nullable = false, length = 50)
    var destination: String = ""

    @Column(nullable = false, precision = 10, scale = 2)
    var price: BigDecimal? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    var airlineId: Airline? = null

    @ManyToMany
    @JoinTable(name = "flight_booking",
            joinColumns = [JoinColumn(name = "flight_id")],
            inverseJoinColumns = [JoinColumn(name = "booking_id")]
    )
    var bookings: MutableSet<Booking>? = null
}