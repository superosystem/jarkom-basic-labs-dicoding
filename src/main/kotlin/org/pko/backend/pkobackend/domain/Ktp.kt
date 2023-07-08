package org.pko.backend.pkobackend.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class Ktp : BaseDomain() {
    @Column(nullable = false)
    var imageUrl: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var userId: User? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ktp_result_id", unique = true)
    var ktpResult: KtpResult? = null
}