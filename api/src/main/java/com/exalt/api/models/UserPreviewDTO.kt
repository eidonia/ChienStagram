package com.exalt.api.models

data class UserPreviewDTO(
    val dateOfBirth: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: String,
    val lastName: String,
    val location: Location,
    val phone: String,
    val picture: String
)
