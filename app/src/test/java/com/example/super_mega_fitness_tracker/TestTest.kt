package com.example.super_mega_fitness_tracker

import org.junit.Test

class TestTest {
    data class NewsItem(
        val title: String,
        val body: String = "bonjour",
        val imageUrl: String = "bonjour.jpg",
    )

    @Test
    fun `WHEN something is called THEN error occurs`() {
        val news = listOf(
            NewsItem("first"), NewsItem("second"), NewsItem("third")
        )
        news.map { it.copy(body = "дружок") }
            .filter { it.title.contains('t') }
            .take(5)
//            .first { it.body == "дружок" }
            .also {
                println(it)
            }
    }
}