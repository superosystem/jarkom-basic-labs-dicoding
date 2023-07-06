package org.pko.backend.pkobackend.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.time.OffsetDateTime
import java.util.UUID
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@EntityListeners(AuditingEntityListener::class)
class Booking {

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

    @Column(
        nullable = false,
        length = 10
    )
    var status: String? = null

    @Column(
        nullable = false,
        unique = true,
        length = 100
    )
    var bookingCode: String? = null

    @Column(nullable = false)
    var passengerName: String? = null

    @Column(
        nullable = false,
        length = 10
    )
    var passengerTitle: String? = null

    @ManyToMany(mappedBy = "bookings")
    var users: MutableSet<User>? = null

    @ManyToMany(mappedBy = "bookings")
    var flights: MutableSet<Flight>? = null

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
