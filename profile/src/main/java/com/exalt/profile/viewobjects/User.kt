package com.exalt.profile.viewobjects


data class User(
    val dateOfBirth: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val location: Location,
    val phone: String,
    val picture: String,
    val registerDate: String,
    val title: String,
    val updatedDate: String
)