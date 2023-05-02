package com.exalt.domain.post.models

import com.exalt.domain.home.models.OwnerPreviewModel

data class CommentPreviewModel(
    val id: String,
    val message: String,
    val owner: OwnerPreviewModel,
    val post: String,
    val publishDate: String
)