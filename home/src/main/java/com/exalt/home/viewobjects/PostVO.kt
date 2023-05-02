package com.exalt.home.viewobjects

data class PostVO (
    val id: String,
    val text: String,
    val imageUri: String,
    val publishDate: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPictureUri: String
)