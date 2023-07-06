package org.pko.backend.pkobackend.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@EntityListeners(AuditingEntityListener::class)
class Flight {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @GeneratedValue(generator = "uuid")
    var id: UUID? = null

    @Column(nullable = false)
    var departTime: OffsetDateTime? = null

    @Column(nullable = false)
    var arrivalTime: OffsetDateTime? = null

    @Column(
        nullable = false,
        length = 50
    )
    var departure: String? = null

    @Column(
        nullable = false,
        length = 50
    )
    var destination: String? = null

    @Column(
        nullable = false,
        precision = 10,
        scale = 2
    )
    var price: BigDecimal? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id_id")
    var airlineId: Airline? = null

    @ManyToMany
    @JoinTable(
        name = "flight_booking",
        joinColumns = [
            JoinColumn(name = "flight_id")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "booking_id")
        ]
    )
    var bookings: MutableSet<Booking>? = null

    @CreatedDate
    @Column(
        nullable = false,
        updatable = false
    )
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    @Column(nullable = false)
    var lastUpdated: OffsetDateTime? = null

}
