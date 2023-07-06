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
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.OffsetDateTime
import java.util.UUID
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "\"user\"")
@EntityListeners(AuditingEntityListener::class)
class User {

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
        length = 150
    )
    var name: String? = null

    @Column(
        nullable = false,
        unique = true,
        length = 100
    )
    var email: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column(columnDefinition = "text")
    var avatar: String? = null

    @OneToMany(mappedBy = "userId")
    var ktps: MutableSet<Ktp>? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "authentication_id",
        unique = true
    )
    var authentication: Authentication? = null

    @ManyToMany
    @JoinTable(
        name = "user_booking",
        joinColumns = [
            JoinColumn(name = "user_id")
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
