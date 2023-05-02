package com.exalt.domain.post.models

import com.exalt.domain.home.models.OwnerPreviewModel

data class DetailedPostPreviewModel (
    val id: String,
    val text: String,
    val imageUrl: String,
    val likes: Int,
    val tags: List<String>,
    val publishDate: String,
    val owner: OwnerPreviewModel
    )