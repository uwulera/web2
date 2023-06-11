package com.libraryExample.musicApplication.datasource.mock

import org.assertj.core.api.Assertions.assertThat

class MockMusicDataSourceTest {
    private val mockDataSource = MockMusicDataSource()

    @org.junit.jupiter.api.Test
    fun `should provide a collection of travels`() {
        // when
        val musics = mockDataSource.retrieveMusics()
        // then
        assertThat(musics.size).isGreaterThanOrEqualTo(3)
    }

    @org.junit.jupiter.api.Test
    fun `should provide some mock data`() {
        // when
        val musics = mockDataSource.retrieveMusics()
        // then
        assertThat(musics).allMatch { it.author.isNotBlank() }
        assertThat(musics).anyMatch { it.song.isNotBlank() }
        assertThat(musics).anyMatch { it.text.isNotBlank() }
    }
}