package org.pko.backend.pkobackend.repos

import java.util.UUID
import org.pko.backend.pkobackend.domain.Authentication
import org.springframework.data.jpa.repository.JpaRepository


interface AuthenticationRepository : JpaRepository<Authentication, UUID>
