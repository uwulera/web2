package com.libraryExample.musicApplication.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.libraryExample.musicApplication.model.Music
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class MusicControllerTest @Autowired constructor(
    val mockMvc: MockMvc, val objectMapper: ObjectMapper
) {
    private val baseUrl = "/api/musics"

    @Nested
    @DisplayName("GET /api/musics")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetMusics {

        @Test
        fun `should return all musics`() {
            // when / then
            mockMvc.get(baseUrl).andDo { print() }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].title") {
                    value("Россия")
                }
            }
        }

    }

    @Nested
    @DisplayName("GET /api/musics/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetMusic {

        @Test
        fun `should return single music with the given id`() {
            // given
            val id = 1

            // when / then
            mockMvc.get("$baseUrl/$id").andDo { print() }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.title") {
                    value("Россия")
                }
            }
        }

        @Test
        fun `should return NOT FOUND if the given id does not exist`() {
            // given
            val id = 999

            // when / then
            mockMvc.get("$baseUrl/$id").andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }

    @Nested
    @DisplayName("POST /api/musics")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddMusic {

        @Test
        fun `should add the new music`() {
            // given
            val id = 4
            val author = "Моцарт"
            val song = "Симфония 15"
            val text = "Ляляля"
            val newMusic = Music(id = id, author = author, song = song,  text = text)

            // when
            val performPost = mockMvc.post("/api/musics") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newMusic)
            }

            // then
            performPost.andDo { print() }.andExpect {
                status { isCreated() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(objectMapper.writeValueAsString(newMusic))
                }
            }

            mockMvc.get("/api/travels/${newMusic.id}").andExpect {
                content { json(objectMapper.writeValueAsString(newMusic)) }
            }
        }

        @Test
        fun `should return BAD REQUEST if Music with given id already exist`() {
            // given
            val invalidMusic = Music(id = 1, author = "Моцарт", song = "Симфония 15", text = "Ляляля")

            // when
            val performPost = mockMvc.post("/api/musics") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidMusic)
            }

            // then
            performPost.andDo { print() }.andExpect {
                status { isBadRequest() }
            }
        }
    }

    @Nested
    @DisplayName("PATCH /api/musics")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingMusic {

        @Test
        fun `should update the music with the given id`() {
            // given
            val id = 1
            val updatedMusic = Music(id = id, author = "Моцарт", song = "Симфония 15", text = "Ляляля")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedMusic)
            }

            // then
            performPatchRequest.andDo { print() }.andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(objectMapper.writeValueAsString(updatedMusic))
                }
            }

            mockMvc.get("/api/musics/$id").andExpect {
                content { json(objectMapper.writeValueAsString(updatedMusic)) }
            }
        }

        @Test
        fun `should return NOT FOUND if the given id does not exist`() {
            // given
            val id = 999
            val updatedMusic = Music(id = id, author = "Моцарт", song = "Симфония 15", text = "Ляляля")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedMusic)
            }

            // then
            performPatchRequest.andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }

    @Nested
    @DisplayName("DELETE /api/musics/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingMusic {

        @Test
        fun `should delete the travel with the given id`() {
            // given
            val id = 1

            // when
            val performDeleteRequest = mockMvc.delete("$baseUrl/$id")

            // then
            performDeleteRequest.andDo { print() }.andExpect {
                status { isNoContent() }
            }

            mockMvc.get("$baseUrl/$id").andExpect {
                status { isNotFound() }
            }
        }

        @Test
        fun `should return NOT FOUND if the given id does not exist`() {
            // given
            val id = 999

            // when
            val performDeleteRequest = mockMvc.delete("$baseUrl/$id")

            // then
            performDeleteRequest.andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }
}