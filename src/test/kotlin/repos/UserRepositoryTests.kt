package org.pko.backend.pkobackend.repos

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.pko.backend.pkobackend.domain.User
import org.pko.backend.pkobackend.repos.UserRepository
import java.time.OffsetDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserRepositoryTests {
    @Mock
    private lateinit var userRepository: UserRepository

    @Test
    fun testSaveUser_ReturnsSavedUser() {
        val user = User()
        user.id = UUID.randomUUID()
        user.name = "John Doe"
        user.email = "johndoe@example.com"
        user.password = "passwd123"
        user.role = "ROLE_USER"
        user.avatar = "https://avatar.com"
        user.dateCreated = OffsetDateTime.now()

        Mockito.`when`(userRepository.save(any())).thenReturn(user)

        val savedUser = userRepository.save(user)

        assertNotNull(savedUser)
        assertEquals(user.name, savedUser.name)
        assertEquals(user.email, savedUser.email)
    }

    @Test
    fun testExistsByEmailIgnoreCase_ExistingEmail_ReturnsTrue() {
        val email = "test@example.com"
        Mockito.`when`(userRepository.existsByEmailIgnoreCase(email)).thenReturn(true)

        val exists = userRepository.existsByEmailIgnoreCase(email)

        assertTrue(exists)
    }

    @Test
    fun testExistsByEmailIgnoreCase_NonExistingEmail_ReturnsFalse() {
        val email = "test@example.com"
        Mockito.`when`(userRepository.existsByEmailIgnoreCase(email)).thenReturn(false)

        val exists = userRepository.existsByEmailIgnoreCase(email)

        assertFalse(exists)
    }
}