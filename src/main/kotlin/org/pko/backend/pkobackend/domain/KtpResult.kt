package org.pko.backend.pkobackend.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class KtpResult : BaseDomain() {
    @Column(nullable = false, length = 5)
    var title: String = ""

    @Column(nullable = false)
    var name: String = ""

    @Column(nullable = false)
    var nationality: String = ""

    @Column(nullable = false)
    var nik: Long? = null

    @Column(nullable = false, length = 20)
    var sex: String = ""

    @Column(nullable = false)
    var married: String = ""

    @OneToOne(mappedBy = "ktpResult", fetch = FetchType.LAZY)
    var ktpId: Ktp? = null
}