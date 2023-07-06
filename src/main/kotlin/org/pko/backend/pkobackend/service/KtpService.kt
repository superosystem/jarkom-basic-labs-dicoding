package org.pko.backend.pkobackend.service

import java.util.UUID
import org.pko.backend.pkobackend.domain.Ktp
import org.pko.backend.pkobackend.model.KtpDTO
import org.pko.backend.pkobackend.repos.KtpRepository
import org.pko.backend.pkobackend.repos.KtpResultRepository
import org.pko.backend.pkobackend.repos.UserRepository
import org.pko.backend.pkobackend.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class KtpService(
    private val ktpRepository: KtpRepository,
    private val userRepository: UserRepository,
    private val ktpResultRepository: KtpResultRepository
) {

    fun findAll(): List<KtpDTO> {
        val ktps = ktpRepository.findAll(Sort.by("id"))
        return ktps.stream()
                .map { ktp -> mapToDTO(ktp, KtpDTO()) }
                .toList()
    }

    fun `get`(id: UUID): KtpDTO = ktpRepository.findById(id)
            .map { ktp -> mapToDTO(ktp, KtpDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(ktpDTO: KtpDTO): UUID {
        val ktp = Ktp()
        mapToEntity(ktpDTO, ktp)
        return ktpRepository.save(ktp).id!!
    }

    fun update(id: UUID, ktpDTO: KtpDTO) {
        val ktp = ktpRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(ktpDTO, ktp)
        ktpRepository.save(ktp)
    }

    fun delete(id: UUID) {
        ktpRepository.deleteById(id)
    }

    private fun mapToDTO(ktp: Ktp, ktpDTO: KtpDTO): KtpDTO {
        ktpDTO.id = ktp.id
        ktpDTO.imageUrl = ktp.imageUrl
        ktpDTO.userId = ktp.userId?.id
        ktpDTO.ktpResult = ktp.ktpResult?.id
        return ktpDTO
    }

    private fun mapToEntity(ktpDTO: KtpDTO, ktp: Ktp): Ktp {
        ktp.imageUrl = ktpDTO.imageUrl
        val userId = if (ktpDTO.userId == null) null else userRepository.findById(ktpDTO.userId!!)
                .orElseThrow { NotFoundException("userId not found") }
        ktp.userId = userId
        val ktpResult = if (ktpDTO.ktpResult == null) null else
                ktpResultRepository.findById(ktpDTO.ktpResult!!)
                .orElseThrow { NotFoundException("ktpResult not found") }
        ktp.ktpResult = ktpResult
        return ktp
    }

}
