package org.pko.backend.pkobackend.repos

import java.util.UUID
import org.pko.backend.pkobackend.domain.KtpResult
import org.springframework.data.jpa.repository.JpaRepository


interface KtpResultRepository : JpaRepository<KtpResult, UUID>
