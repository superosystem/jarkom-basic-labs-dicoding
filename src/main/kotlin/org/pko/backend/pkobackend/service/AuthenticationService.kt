package org.pko.backend.pkobackend.service

import java.util.UUID
import org.pko.backend.pkobackend.domain.Authentication
import org.pko.backend.pkobackend.model.AuthenticationDTO
import org.pko.backend.pkobackend.repos.AuthenticationRepository
import org.pko.backend.pkobackend.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class AuthenticationService(
    private val authenticationRepository: AuthenticationRepository
) {

    fun findAll(): List<AuthenticationDTO> {
        val authentications = authenticationRepository.findAll(Sort.by("id"))
        return authentications.stream()
                .map { authentication -> mapToDTO(authentication, AuthenticationDTO()) }
                .toList()
    }

    fun `get`(id: UUID): AuthenticationDTO = authenticationRepository.findById(id)
            .map { authentication -> mapToDTO(authentication, AuthenticationDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(authenticationDTO: AuthenticationDTO): UUID {
        val authentication = Authentication()
        mapToEntity(authenticationDTO, authentication)
        return authenticationRepository.save(authentication).id!!
    }

    fun update(id: UUID, authenticationDTO: AuthenticationDTO) {
        val authentication = authenticationRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(authenticationDTO, authentication)
        authenticationRepository.save(authentication)
    }

    fun delete(id: UUID) {
        authenticationRepository.deleteById(id)
    }

    private fun mapToDTO(authentication: Authentication, authenticationDTO: AuthenticationDTO):
            AuthenticationDTO {
        authenticationDTO.id = authentication.id
        authenticationDTO.token = authentication.token
        authenticationDTO.expiredAt = authentication.expiredAt
        return authenticationDTO
    }

    private fun mapToEntity(authenticationDTO: AuthenticationDTO, authentication: Authentication):
            Authentication {
        authentication.token = authenticationDTO.token
        authentication.expiredAt = authenticationDTO.expiredAt
        return authentication
    }

}
