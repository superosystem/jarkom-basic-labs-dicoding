package org.pko.backend.pkobackend.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class Airline : BaseDomain() {
    @Column(nullable = false)
    var airline: String = ""

    @Column(nullable = false)
    var icon: String = ""

    @OneToMany(mappedBy = "airlineId")
    var flights: MutableSet<Flight>? = null
}
