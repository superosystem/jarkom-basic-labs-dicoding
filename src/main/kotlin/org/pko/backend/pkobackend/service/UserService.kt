package org.pko.backend.pkobackend.service

import org.pko.backend.pkobackend.model.user.RegisterRequest

interface UserService {
    fun signUp(request: RegisterRequest)
}