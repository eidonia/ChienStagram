package com.exalt.post.viewobjects

data class PostFlow(
    val isLoading: Boolean = false,
    val post: DetailedPost? = null,
    val message: String? = null
)