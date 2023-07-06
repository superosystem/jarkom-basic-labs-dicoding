package org.pko.backend.pkobackend.service

import java.util.UUID
import org.pko.backend.pkobackend.domain.Airline
import org.pko.backend.pkobackend.model.AirlineDTO
import org.pko.backend.pkobackend.repos.AirlineRepository
import org.pko.backend.pkobackend.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class AirlineService(
    private val airlineRepository: AirlineRepository
) {

    fun findAll(): List<AirlineDTO> {
        val airlines = airlineRepository.findAll(Sort.by("id"))
        return airlines.stream()
                .map { airline -> mapToDTO(airline, AirlineDTO()) }
                .toList()
    }

    fun `get`(id: UUID): AirlineDTO = airlineRepository.findById(id)
            .map { airline -> mapToDTO(airline, AirlineDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(airlineDTO: AirlineDTO): UUID {
        val airline = Airline()
        mapToEntity(airlineDTO, airline)
        return airlineRepository.save(airline).id!!
    }

    fun update(id: UUID, airlineDTO: AirlineDTO) {
        val airline = airlineRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(airlineDTO, airline)
        airlineRepository.save(airline)
    }

    fun delete(id: UUID) {
        airlineRepository.deleteById(id)
    }

    private fun mapToDTO(airline: Airline, airlineDTO: AirlineDTO): AirlineDTO {
        airlineDTO.id = airline.id
        airlineDTO.airline = airline.airline
        airlineDTO.icon = airline.icon
        return airlineDTO
    }

    private fun mapToEntity(airlineDTO: AirlineDTO, airline: Airline): Airline {
        airline.airline = airlineDTO.airline
        airline.icon = airlineDTO.icon
        return airline
    }

}
