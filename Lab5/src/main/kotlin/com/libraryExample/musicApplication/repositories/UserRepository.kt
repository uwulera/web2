package com.libraryExample.musicApplication.repositories

import com.libraryExample.musicApplication.model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, Int> {
    fun findByEmail(email: String): Users?
}