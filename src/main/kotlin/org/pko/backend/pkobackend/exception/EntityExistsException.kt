package org.pko.backend.pkobackend.exception

class EntityExistsException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

}