package org.pko.backend.pkobackend.services

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.pko.backend.pkobackend.domain.User
import org.pko.backend.pkobackend.exception.EntityExistsException
import org.pko.backend.pkobackend.model.user.RegisterRequest
import org.pko.backend.pkobackend.repos.UserRepository
import org.pko.backend.pkobackend.service.impl.UserServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockitoExtension::class)
class UserServiceImplTests {
    @Mock
    private lateinit var repository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    private lateinit var userService: UserServiceImpl

    @Test
    fun testSignUp_ValidRequest_SuccessfulSignUp() {
        // Prepare test data
        val request = RegisterRequest(
                name = "John Doe",
                email = "john@example.com",
                password = "password123",
                avatar = "avatar.jpg"
        )
        val encodedPassword = "encodedPassword"

        // Mock repository behavior
        `when`(repository.existsByEmailIgnoreCase(request.email)).thenReturn(false)
        `when`(passwordEncoder.encode(request.password)).thenReturn(encodedPassword)
        `when`(repository.save(any(User::class.java))).thenReturn(User())

        // Perform the sign-up operation
        userService.signUp(request)

        // Verify interactions and assertions
        verify(repository, times(1)).existsByEmailIgnoreCase(request.email)
        verify(passwordEncoder, times(1)).encode(request.password)
        verify(repository, times(1)).save(any(User::class.java))
    }

    @Test
    fun testSignUp_DuplicateEmail_ThrowsEntityExistsException() {
        // Prepare test data
        val request = RegisterRequest(
                name = "John Doe",
                email = "john@example.com",
                password = "password123",
                avatar = "avatar.jpg"
        )

        // Mock repository behavior
        `when`(repository.existsByEmailIgnoreCase(request.email)).thenReturn(true)

        // Perform the sign-up operation and assert that it throws EntityExistsException
        assertThrows(EntityExistsException::class.java) {
            userService.signUp(request)
        }

        // Verify interactions
        verify(repository, times(1)).existsByEmailIgnoreCase(request.email)
        verifyNoMoreInteractions(passwordEncoder, repository)
    }
}