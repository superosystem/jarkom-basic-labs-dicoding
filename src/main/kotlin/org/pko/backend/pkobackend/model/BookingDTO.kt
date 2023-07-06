package org.pko.backend.pkobackend.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class BookingDTO {

    var id: UUID? = null

    @NotNull
    @Size(max = 10)
    var status: String? = null

    @NotNull
    @Size(max = 100)
    var bookingCode: String? = null

    @NotNull
    @Size(max = 255)
    var passengerName: String? = null

    @NotNull
    @Size(max = 10)
    var passengerTitle: String? = null

}
