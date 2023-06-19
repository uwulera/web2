package com.libraryExample.musicApplication.service

import com.libraryExample.musicApplication.datasource.MusicDataSource
import com.libraryExample.musicApplication.model.Music
import org.springframework.stereotype.Service

@Service
class MusicService(private val dataSource: MusicDataSource) {
    fun getMusics(): Collection<Music> = dataSource.retrieveMusics()
    fun getMusic(id: Int) = dataSource.retrieveMusic(id)
    fun addMusic(music: Music): Music = dataSource.createMusic(music)
    fun updateMusic(music: Music): Music = dataSource.updateMusic(music)
    fun deleteMusic(id: Int): Unit = dataSource.deleteMusic(id)
}
