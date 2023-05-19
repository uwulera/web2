import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import com.example.json.Emoji
import com.example.json.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


class JsonSerializerTest {

    private val serializer = JsonSerializer()
    private val mapper: ObjectMapper = jacksonObjectMapper()

    @Test
    fun testDeserialization() {
        val json = """
        {
            "name": "person with turban, type-4",
            "category": "smileys and people",
            "group": "person role",
            "htmlCode": ["&#128115;","&#127997;"],
            "unicode": ["U+1F473","U+1F3FD"]
        }
        """

        val emoji = serializer.deserialize<Emoji>(json)

        Assertions.assertEquals("person with turban, type-4", emoji.name)
        Assertions.assertEquals("smileys and people", emoji.category)
        Assertions.assertEquals("person role", emoji.group)
        Assertions.assertEquals(listOf("&#128115;", "&#127997;"), emoji.htmlCode)
        Assertions.assertEquals(listOf("U+1F473", "U+1F3FD"), emoji.unicode)
    }

    @Test
    fun testSerialization() {
        val emoji = Emoji(
            name = "person with turban, type-4",
            category = "smileys and people",
            group = "person role",
            htmlCode = listOf("&#128115;", "&#127997;"),
            unicode = listOf("U+1F473", "U+1F3FD")
        )

        val json = serializer.serialize(emoji)

        val expectedJson = """
        {
            "name": "person with turban, type-4",
            "category": "smileys and people",
            "group": "person role",
            "htmlCode": ["&#128115;","&#127997;"],
            "unicode": ["U+1F473","U+1F3FD"]
        }
        """.trimIndent()

        val expectedEmoji = mapper.readValue(expectedJson, Emoji::class.java)
        val actualEmoji = mapper.readValue(json, Emoji::class.java)

        Assertions.assertEquals(expectedEmoji, actualEmoji)
    }
}
