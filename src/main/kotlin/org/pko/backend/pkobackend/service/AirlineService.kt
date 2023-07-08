//package org.pko.backend.pkobackend.service
//
//import java.util.UUID
//import org.pko.backend.pkobackend.domain.Airline
//import org.pko.backend.pkobackend.model.AirlineDTO
//import org.pko.backend.pkobackend.repos.AirlineRepository
//import org.pko.backend.pkobackend.exception.NotFoundException
//import org.springframework.data.domain.Sort
//import org.springframework.stereotype.Service
//import java.time.OffsetDateTime
//
//
//@Service
//class AirlineService(
//    private val airlineRepository: AirlineRepository
//) {
//
//    fun findAll(): List<AirlineDTO> {
//        val airlines = airlineRepository.findAll(Sort.by("id"))
//        return airlines.stream()
//                .map { airline -> mapToDTO(airline, AirlineDTO()) }
//                .toList()
//    }
//
//    fun `get`(id: UUID): AirlineDTO = airlineRepository.findById(id)
//            .map { airline -> mapToDTO(airline, AirlineDTO()) }
//            .orElseThrow { NotFoundException() }
//
//    fun create(airlineDTO: AirlineDTO): UUID {
//
//        return airlineRepository.save(mapToEntity(airlineDTO)).id
//    }
//
//    fun update(id: UUID, airlineDTO: AirlineDTO) {
//        val airline = airlineRepository.findById(id)
//                .orElseThrow { NotFoundException() }
//        mapToEntity(airlineDTO)
//        airlineRepository.save(airline)
//    }
//
//    fun delete(id: UUID) {
//        airlineRepository.deleteById(id)
//    }
//
//    private fun mapToDTO(airline: Airline, airlineDTO: AirlineDTO): AirlineDTO {
//        airlineDTO.id = airline.id
//        airlineDTO.airline = airline.airline
//        airlineDTO.icon = airline.icon
//        return airlineDTO
//    }
//
//    private fun mapToEntity(dto: AirlineDTO): Airline {
//        return Airline(
//                id = UUID.randomUUID(),
//                airline = dto.airline!!,
//                icon = dto.icon!!,
//                flights = null,
//                dateCreated = OffsetDateTime.now(),
//                lastUpdated = null,
//        )
//    }
//
//}
