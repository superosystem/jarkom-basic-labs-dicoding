//package org.pko.backend.pkobackend.model
//
//import com.fasterxml.jackson.annotation.JsonFormat
//import io.swagger.v3.oas.annotations.media.Schema
//import jakarta.validation.constraints.Digits
//import jakarta.validation.constraints.NotNull
//import jakarta.validation.constraints.Size
//import java.math.BigDecimal
//import java.time.OffsetDateTime
//import java.util.UUID
//
//
//class FlightDTO {
//
//    var id: UUID? = null
//
//    @NotNull
//    var departTime: OffsetDateTime? = null
//
//    @NotNull
//    var arrivalTime: OffsetDateTime? = null
//
//    @NotNull
//    @Size(max = 50)
//    var departure: String? = null
//
//    @NotNull
//    @Size(max = 50)
//    var destination: String? = null
//
//    @NotNull
//    @Digits(
//        integer = 10,
//        fraction = 2
//    )
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    @Schema(
//        type = "string",
//        example = "33.08"
//    )
//    var price: BigDecimal? = null
//
//    var airlineId: UUID? = null
//
//    var bookings: List<UUID>? = null
//
//}
