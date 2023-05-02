package com.exalt.post.viewobjects

data class DetailedPost (
    val id: String,
    val text: String,
    val imageUri: String,
    val publishDate: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPictureUri: String,
    val tags: List<String>,
    val likes: Int
)