package org.pko.backend.pkobackend.repos

import java.util.UUID
import org.pko.backend.pkobackend.domain.Ktp
import org.springframework.data.jpa.repository.JpaRepository


interface KtpRepository : JpaRepository<Ktp, UUID>
