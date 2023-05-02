package com.exalt.post.viewobjects

data class Comment(
    val id: String,
    val message: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPictureUrl: String,
    val post: String,
    val publishDate: String
)