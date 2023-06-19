package com.libraryExample.musicApplication.controller

import com.libraryExample.musicApplication.dtos.LoginDTO
import com.libraryExample.musicApplication.dtos.Message
import com.libraryExample.musicApplication.dtos.RegisterDTO
import com.libraryExample.musicApplication.model.Users
import com.libraryExample.musicApplication.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.crypto.SecretKey

@RestController
@RequestMapping("api")
class AuthController(private val service: UserService) {


    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Users> {
        val users = Users()
        users.name = body.name
        users.email = body.email
        users.password = body.password

        return ResponseEntity.ok(this.service.save(users))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val user =
            this.service.findByEmail(body.email) ?: return ResponseEntity.badRequest().body(Message("User not found"))

        if (!user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body(Message("Invalid password"))
        }

        val issuer = user.id.toString()

        val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

        val jwt = Jwts.builder().setIssuer(issuer).setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
            .signWith(key).compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Logged in"))
    }


}