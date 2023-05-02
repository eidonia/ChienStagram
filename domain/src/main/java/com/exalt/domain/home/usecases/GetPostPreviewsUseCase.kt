package com.exalt.domain.home.usecases

import com.exalt.domain.home.models.PostPreviewModel
import com.exalt.domain.home.repositories.PostPreviewRepository
import javax.inject.Inject

class GetPostPreviewsUseCase @Inject constructor(
    private val postPreviewRepository: PostPreviewRepository
) {
    suspend fun invoke(): List<PostPreviewModel> = runCatching {
        postPreviewRepository.getPosts(0u)
    }.getOrDefault(emptyList())
}