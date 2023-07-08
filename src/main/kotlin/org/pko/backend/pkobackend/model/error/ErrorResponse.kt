package org.pko.backend.pkobackend.model.error

data class ErrorResponse(
        var httpStatus: Int? = null,
        var exception: String? = null,
        var message: String? = null,
        var fieldErrors: List<FieldError>? = null
)
