package com.exalt.api.models

data class PostPreviewDTO(
    val id: String,
    val text: String,
    val image: String,
    val likes: Int,
    val tags: List<String>,
    val publishDate: String,
    val owner: UserPreviewDTO
)