package org.pko.backend.pkobackend.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class KtpResultDTO {

    var id: UUID? = null

    @NotNull
    @Size(max = 5)
    var title: String? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    @Size(max = 255)
    var nationality: String? = null

    @NotNull
    var nik: Long? = null

    @NotNull
    @Size(max = 20)
    var sex: String? = null

    @NotNull
    @Size(max = 255)
    var married: String? = null

}
