package com.exalt.domain.home.repositories

import com.exalt.domain.home.models.PostPreviewModel

interface PostPreviewRepository {
    suspend fun getPosts(page: UInt): List<PostPreviewModel>
}