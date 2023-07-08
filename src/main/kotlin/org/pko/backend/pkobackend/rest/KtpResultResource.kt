//package org.pko.backend.pkobackend.rest
//
//import io.swagger.v3.oas.annotations.responses.ApiResponse
//import jakarta.validation.Valid
//import java.lang.Void
//import java.util.UUID
//import org.pko.backend.pkobackend.model.KtpResultDTO
//import org.pko.backend.pkobackend.service.KtpResultService
//import org.springframework.http.HttpStatus
//import org.springframework.http.MediaType
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.DeleteMapping
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.PutMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//
//@RestController
//@RequestMapping(
//    value = ["/api/ktpResults"],
//    produces = [MediaType.APPLICATION_JSON_VALUE]
//)
//class KtpResultResource(
//    private val ktpResultService: KtpResultService
//) {
//
//    @GetMapping
//    fun getAllKtpResults(): ResponseEntity<List<KtpResultDTO>> =
//            ResponseEntity.ok(ktpResultService.findAll())
//
//    @GetMapping("/{id}")
//    fun getKtpResult(@PathVariable(name = "id") id: UUID): ResponseEntity<KtpResultDTO> =
//            ResponseEntity.ok(ktpResultService.get(id))
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    fun createKtpResult(@RequestBody @Valid ktpResultDTO: KtpResultDTO): ResponseEntity<UUID> {
//        val createdId = ktpResultService.create(ktpResultDTO)
//        return ResponseEntity(createdId, HttpStatus.CREATED)
//    }
//
//    @PutMapping("/{id}")
//    fun updateKtpResult(@PathVariable(name = "id") id: UUID, @RequestBody @Valid
//            ktpResultDTO: KtpResultDTO): ResponseEntity<UUID> {
//        ktpResultService.update(id, ktpResultDTO)
//        return ResponseEntity.ok(id)
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiResponse(responseCode = "204")
//    fun deleteKtpResult(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
//        ktpResultService.delete(id)
//        return ResponseEntity.noContent().build()
//    }
//
//}
