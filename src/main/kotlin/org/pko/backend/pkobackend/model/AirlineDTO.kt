package org.pko.backend.pkobackend.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class AirlineDTO {

    var id: UUID? = null

    @NotNull
    @Size(max = 255)
    var airline: String? = null

    @NotNull
    @Size(max = 255)
    var icon: String? = null

}
