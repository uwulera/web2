package com.libraryExample.musicApplication.service

import com.libraryExample.musicApplication.datasource.MusicDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class MusicServiceTest {
    private val dataSource: MusicDataSource = mockk(relaxed = true)
    private val musicService = MusicService(dataSource)

    @Test
    fun `should call its data source to retrieve musics`() {
        // when
        musicService.getMusics()

        // then
        verify(exactly = 1) { dataSource.retrieveMusics() }
    }
}