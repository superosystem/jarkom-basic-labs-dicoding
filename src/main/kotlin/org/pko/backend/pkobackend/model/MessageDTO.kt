package org.pko.backend.pkobackend.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageDTO(
        @JsonProperty("message")
        var message: String
)
