package com.exalt.domain.post.usecases

import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.domain.post.repositories.DetailedPostPreviewRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCommentsPreviewUseCase @Inject constructor(
    private val postPreviewRepository: DetailedPostPreviewRepository
) {

    operator fun invoke(postId: String) = flow {
        try {
            emit(CommentsPreview.Loading)
            val data = postPreviewRepository.getComment(postId)
            emit(CommentsPreview.Success(data = data))
        } catch (e: IOException) {
            emit(CommentsPreview.Error(message = e.localizedMessage))
        }
    }
}

sealed class CommentsPreview(val data: List<CommentPreviewModel>? = null, val message: String? = null) {
    object Loading: CommentsPreview()
    class Success(data: List<CommentPreviewModel>?): CommentsPreview(data = data)
    class Error(message: String?): CommentsPreview(message = message)
}