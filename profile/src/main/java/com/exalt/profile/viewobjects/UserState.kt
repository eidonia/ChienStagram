package com.exalt.profile.viewobjects

data class UserState(
    var isLoading: Boolean = false,
    val data: User? = null,
    val message: String = ""
)
