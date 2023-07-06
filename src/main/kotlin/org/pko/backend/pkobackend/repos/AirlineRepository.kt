package org.pko.backend.pkobackend.repos

import java.util.UUID
import org.pko.backend.pkobackend.domain.Airline
import org.springframework.data.jpa.repository.JpaRepository


interface AirlineRepository : JpaRepository<Airline, UUID>
