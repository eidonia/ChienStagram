package com.exalt.domain.home.models

data class PostPreviewModel (
    val id: String,
    val text: String,
    val imageUrl: String,
    val publishDate: String,
    val owner: OwnerPreviewModel,
)
