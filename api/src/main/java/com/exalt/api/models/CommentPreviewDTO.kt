package com.exalt.api.models

data class CommentPreviewDTO(
    val id: String,
    val message: String,
    val owner: UserPreviewDTO,
    val post: String,
    val publishDate: String
)