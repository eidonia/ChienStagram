package com.exalt.post.viewobjects

data class CommentFlow(
    val isLoading: Boolean = false,
    val data: List<Comment> = emptyList(),
    val message: String? = null
)