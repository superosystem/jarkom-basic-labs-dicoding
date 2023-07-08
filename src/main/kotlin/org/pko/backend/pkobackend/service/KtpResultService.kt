//package org.pko.backend.pkobackend.service
//
//import java.util.UUID
//import org.pko.backend.pkobackend.domain.KtpResult
//import org.pko.backend.pkobackend.model.KtpResultDTO
//import org.pko.backend.pkobackend.repos.KtpResultRepository
//import org.pko.backend.pkobackend.exception.NotFoundException
//import org.springframework.data.domain.Sort
//import org.springframework.stereotype.Service
//
//
//@Service
//class KtpResultService(
//    private val ktpResultRepository: KtpResultRepository
//) {
//
//    fun findAll(): List<KtpResultDTO> {
//        val ktpResults = ktpResultRepository.findAll(Sort.by("id"))
//        return ktpResults.stream()
//                .map { ktpResult -> mapToDTO(ktpResult, KtpResultDTO()) }
//                .toList()
//    }
//
//    fun `get`(id: UUID): KtpResultDTO = ktpResultRepository.findById(id)
//            .map { ktpResult -> mapToDTO(ktpResult, KtpResultDTO()) }
//            .orElseThrow { NotFoundException() }
//
//    fun create(ktpResultDTO: KtpResultDTO): UUID {
//        val ktpResult = KtpResult()
//        mapToEntity(ktpResultDTO, ktpResult)
//        return ktpResultRepository.save(ktpResult).id!!
//    }
//
//    fun update(id: UUID, ktpResultDTO: KtpResultDTO) {
//        val ktpResult = ktpResultRepository.findById(id)
//                .orElseThrow { NotFoundException() }
//        mapToEntity(ktpResultDTO, ktpResult)
//        ktpResultRepository.save(ktpResult)
//    }
//
//    fun delete(id: UUID) {
//        ktpResultRepository.deleteById(id)
//    }
//
//    private fun mapToDTO(ktpResult: KtpResult, ktpResultDTO: KtpResultDTO): KtpResultDTO {
//        ktpResultDTO.id = ktpResult.id
//        ktpResultDTO.title = ktpResult.title
//        ktpResultDTO.name = ktpResult.name
//        ktpResultDTO.nationality = ktpResult.nationality
//        ktpResultDTO.nik = ktpResult.nik
//        ktpResultDTO.sex = ktpResult.sex
//        ktpResultDTO.married = ktpResult.married
//        return ktpResultDTO
//    }
//
//    private fun mapToEntity(ktpResultDTO: KtpResultDTO, ktpResult: KtpResult): KtpResult {
//        ktpResult.title = ktpResultDTO.title
//        ktpResult.name = ktpResultDTO.name
//        ktpResult.nationality = ktpResultDTO.nationality
//        ktpResult.nik = ktpResultDTO.nik
//        ktpResult.sex = ktpResultDTO.sex
//        ktpResult.married = ktpResultDTO.married
//        return ktpResult
//    }
//
//}
