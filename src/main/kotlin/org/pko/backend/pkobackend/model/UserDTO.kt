package org.pko.backend.pkobackend.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class UserDTO {

    var id: UUID? = null

    @NotNull
    @Size(max = 150)
    var name: String? = null

    @NotNull
    @Size(max = 100)
    var email: String? = null

    @NotNull
    @Size(max = 255)
    var password: String? = null

    var avatar: String? = null

    var authentication: UUID? = null

    var bookings: List<UUID>? = null

}
