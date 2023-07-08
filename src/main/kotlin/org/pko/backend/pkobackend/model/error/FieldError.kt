package org.pko.backend.pkobackend.model.error

data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null
)
