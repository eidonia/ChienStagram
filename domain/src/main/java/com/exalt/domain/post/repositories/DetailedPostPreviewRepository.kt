package com.exalt.domain.post.repositories

import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.domain.home.models.PostPreviewModel
import com.exalt.domain.post.models.DetailedPostPreviewModel

interface DetailedPostPreviewRepository {
    suspend fun getPost(id: String): DetailedPostPreviewModel?
    suspend fun getComment(id: String): List<CommentPreviewModel>
}