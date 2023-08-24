package com.exalt.domain.profile.models

data class UserPreviewModel(
    val dateOfBirth: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val location: LocationPreviewModel,
    val phone: String,
    val picture: String,
    val registerDate: String,
    val title: String,
    val updatedDate: String
)
