package com.exalt.data.repositories

import com.exalt.api.services.PostService
import com.exalt.data.mappers.PostPreviewMapper
import com.exalt.domain.home.models.PostPreviewModel
import com.exalt.domain.home.repositories.PostPreviewRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PostPreviewRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val postPreviewMapper: PostPreviewMapper,
) : PostPreviewRepository {
    override suspend fun getPosts(page: UInt): List<PostPreviewModel> =
        postService.getPosts(page).let { response ->
            if (response.isSuccessful) {
                postPreviewMapper.fromListDto(response.body()!!.data)
            } else {
                emptyList()
            }
        }
}