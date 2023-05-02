package com.exalt.data.repositories

import com.exalt.api.services.PostService
import com.exalt.data.mappers.CommentPreviewMapper
import com.exalt.data.mappers.DetailedPostPreviewMapper
import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.domain.post.models.DetailedPostPreviewModel
import com.exalt.domain.post.repositories.DetailedPostPreviewRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DetailedPostPreviewRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val detailedPostPreviewMapper: DetailedPostPreviewMapper,
    private val commentPreviewMapper: CommentPreviewMapper
) : DetailedPostPreviewRepository {


    override suspend fun getPost(id: String): DetailedPostPreviewModel? =
        postService.getPost(id).let { response ->
            if (response.isSuccessful) {
                detailedPostPreviewMapper.fromDto(response.body()!!)
            } else {
                null
            }
        }


    override suspend fun getComment(id: String): List<CommentPreviewModel> =
        postService.getPostComment(id).let { response ->
            if (response.isSuccessful) {
                commentPreviewMapper.fromListDto(response.body()!!.data)
            } else {
                emptyList()
        }
    }
}