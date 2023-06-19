package com.libraryExample.musicApplication.controller


import com.libraryExample.musicApplication.model.Music
import com.libraryExample.musicApplication.service.MusicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/musics")
class MusicController(private val service: MusicService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getMusics(): Collection<Music> = service.getMusics()

    @GetMapping("/{id}")
    fun getMusic(@PathVariable id: Int): Music = service.getMusic(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addMusic(@RequestBody music: Music): Music = service.addMusic(music)

    @PatchMapping
    fun updateMusic(@RequestBody music: Music): Music = service.updateMusic(music)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMusic(@PathVariable id: Int): Unit = service.deleteMusic(id)
}