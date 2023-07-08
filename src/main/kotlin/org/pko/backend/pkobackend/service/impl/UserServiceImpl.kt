package org.pko.backend.pkobackend.service.impl

import org.pko.backend.pkobackend.domain.User
import org.pko.backend.pkobackend.exception.EntityExistsException
import org.pko.backend.pkobackend.model.user.RegisterRequest
import org.pko.backend.pkobackend.repos.UserRepository
import org.pko.backend.pkobackend.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
        private val repository: UserRepository,
        private val passwordEncoder: PasswordEncoder
) : UserService {

    @Transactional
    override fun signUp(request: RegisterRequest) {
        if (availableEmail(request.email)) {
            throw EntityExistsException("email : ${request.email} already used")
        }

        val user = User()
        user.name = request.name
        user.email = request.email
        user.password = passwordEncoder.encode(request.password)
        user.role = "ROLE_USER"
        user.avatar = request.avatar
        repository.save(user)
    }

    private fun availableEmail(email: String): Boolean {
        return repository.existsByEmailIgnoreCase(email)
    }
}

//import jakarta.transaction.Transactional
//import java.util.UUID
//import org.pko.backend.pkobackend.domain.User
//import org.pko.backend.pkobackend.model.UserDTO
//import org.pko.backend.pkobackend.repos.AuthenticationRepository
//import org.pko.backend.pkobackend.repos.BookingRepository
//import org.pko.backend.pkobackend.repos.UserRepository
//import org.pko.backend.pkobackend.exception.NotFoundException
//import org.springframework.data.domain.Sort
//import org.springframework.stereotype.Service
//
//
//@Service
//@Transactional
//class UserService(
//    private val userRepository: UserRepository,
//    private val authenticationRepository: AuthenticationRepository,
//    private val bookingRepository: BookingRepository
//) {
//
//    fun findAll(): List<UserDTO> {
//        val users = userRepository.findAll(Sort.by("id"))
//        return users.stream()
//                .map { user -> mapToDTO(user, UserDTO()) }
//                .toList()
//    }
//
//    fun `get`(id: UUID): UserDTO = userRepository.findById(id)
//            .map { user -> mapToDTO(user, UserDTO()) }
//            .orElseThrow { NotFoundException() }
//
//    fun create(userDTO: UserDTO): UUID {
//        val user = User()
//        mapToEntity(userDTO, user)
//        return userRepository.save(user).id!!
//    }
//
//    fun update(id: UUID, userDTO: UserDTO) {
//        val user = userRepository.findById(id)
//                .orElseThrow { NotFoundException() }
//        mapToEntity(userDTO, user)
//        userRepository.save(user)
//    }
//
//    fun delete(id: UUID) {
//        userRepository.deleteById(id)
//    }
//
//    private fun mapToDTO(user: User, userDTO: UserDTO): UserDTO {
//        userDTO.id = user.id
//        userDTO.name = user.name
//        userDTO.email = user.email
//        userDTO.password = user.password
//        userDTO.avatar = user.avatar
//        userDTO.authentication = user.authentication?.id
//        userDTO.bookings = user.bookings?.stream()
//                ?.map { booking -> booking.id!! }
//                ?.toList()
//        return userDTO
//    }
//
//    private fun mapToEntity(userDTO: UserDTO, user: User): User {
//        user.name = userDTO.name
//        user.email = userDTO.email
//        user.password = userDTO.password
//        user.avatar = userDTO.avatar
//        val authentication = if (userDTO.authentication == null) null else
//                authenticationRepository.findById(userDTO.authentication!!)
//                .orElseThrow { NotFoundException("authentication not found") }
//        user.authentication = authentication
//        val bookings = bookingRepository.findAllById(userDTO.bookings ?: emptyList())
//        if (bookings.size != (if (userDTO.bookings == null) 0 else userDTO.bookings!!.size)) {
//            throw NotFoundException("one of bookings not found")
//        }
//        user.bookings = bookings.toMutableSet()
//        return user
//    }
//
//    fun emailExists(email: String?): Boolean = userRepository.existsByEmailIgnoreCase(email)
//
//}
