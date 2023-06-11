package com.libraryExample.musicApplication.datasource

import com.libraryExample.musicApplication.model.Music


interface MusicDataSource {
    fun retrieveMusics(): Collection<Music>

    fun retrieveMusic(id: Int): Music

    fun createMusic(music: Music): Music

    fun updateMusic(music: Music): Music

    fun deleteMusic(id: Int): Unit
}
