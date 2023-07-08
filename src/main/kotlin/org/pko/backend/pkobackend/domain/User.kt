package org.pko.backend.pkobackend.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*


@Entity
@Table(name = "\"user\"")
@EntityListeners(AuditingEntityListener::class)
class User : BaseDomain() {
    @Column(nullable = false, length = 150)
    var name: String = ""

    @Column(nullable = false, unique = true, length = 100)
    var email: String = ""

    @Column(nullable = false)
    var password: String = ""

    @Column(name = "role")
    var role: String = ""

    @Column(columnDefinition = "text")
    var avatar: String? = ""

    @OneToMany(mappedBy = "userId")
    var ktps: MutableSet<Ktp>? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_id", unique = true)
    var authentication: Authentication? = null

    @ManyToMany
    @JoinTable(name = "user_booking",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "booking_id")]
    )
    var bookings: MutableSet<Booking>? = null
}