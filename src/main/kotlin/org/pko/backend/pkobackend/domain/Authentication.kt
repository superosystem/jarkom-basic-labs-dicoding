package org.pko.backend.pkobackend.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class Authentication : BaseDomain() {
    @Column(nullable = false)
    var token: String = ""

    @Column(nullable = false)
    var expiredAt: LocalDateTime? = null

    @OneToOne(mappedBy = "authentication", fetch = FetchType.LAZY)
    var user: User? = null
}
