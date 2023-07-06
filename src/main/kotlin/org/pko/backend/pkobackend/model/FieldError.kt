package org.pko.backend.pkobackend.model


data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null
)
