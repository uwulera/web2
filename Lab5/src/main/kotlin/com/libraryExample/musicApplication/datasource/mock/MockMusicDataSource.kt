package com.libraryExample.musicApplication.datasource.mock

import com.libraryExample.musicApplication.datasource.MusicDataSource
import com.libraryExample.musicApplication.model.Music
import org.springframework.stereotype.Repository

@Repository
class MockMusicDataSource : MusicDataSource {

    val musics = mutableListOf(
        Music(1, "Моцарт", "Симфония 15", "Ляляля"),
        Music(2, "Бах", "Симфония 14", "Ля"),
        Music(3, "Чайковский", "Симфония 13", "Ляля"),
    )

    override fun retrieveMusics(): Collection<Music> = musics

    override fun retrieveMusic(id: Int): Music =
        musics.firstOrNull { it.id == id } ?: throw NoSuchElementException("Could not find music with id $id")

    override fun createMusic(music: Music): Music {
        if (musics.any { it.id == music.id }) {
            throw IllegalArgumentException("Music with id ${music.id} already exists")
        }
        musics.add(music)
        return music
    }

    override fun updateMusic(music: Music): Music {
        val currentMusic = musics.firstOrNull { it.id == music.id }
            ?: throw NoSuchElementException("Could not find music with id ${music.id}")

        musics.remove(currentMusic)
        musics.add(music)

        return music
    }

    override fun deleteMusic(id: Int) {
        val currentMusic =
            musics.firstOrNull { it.id == id } ?: throw NoSuchElementException("Could not find music with id $id")

        musics.remove(currentMusic)
    }
}