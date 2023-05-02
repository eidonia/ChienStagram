package com.exalt.domain.post.usecases

import com.exalt.domain.home.models.PostPreviewModel
import com.exalt.domain.post.models.DetailedPostPreviewModel
import com.exalt.domain.post.repositories.DetailedPostPreviewRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetDetailedPostPreviewUseCase @Inject constructor(
    private val postPreviewRepository: DetailedPostPreviewRepository
) {

    operator fun invoke(postId: String) = flow {
        try {
            emit(PostPreview.Loading)
            val data = postPreviewRepository.getPost(postId)
            if (data != null) {
                emit(PostPreview.Success(data = data))
            } else {
                emit(PostPreview.Error(message = "Erreur lors du chargement du post"))
            }

        } catch (e: IOException) {
            emit(PostPreview.Error(message = e.localizedMessage))
        }
    }
}

sealed class PostPreview(val data: DetailedPostPreviewModel? = null, val message: String? = null) {
    object Loading: PostPreview()
    class Success(data: DetailedPostPreviewModel?): PostPreview(data = data)
    class Error(message: String?): PostPreview(message = message)
}