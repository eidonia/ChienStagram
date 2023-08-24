package com.exalt.domain.profile.usecases

import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.domain.profile.models.LocationPreviewModel
import com.exalt.domain.profile.models.UserPreviewModel
import com.exalt.domain.profile.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetProfilePreviewUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    
    operator fun invoke(userId: String) = flow {
        try {
            emit(UserPreview.Loading)
            val data = profileRepository.getUser(userId)
            emit(UserPreview.Success(data = data))
        }  catch (e: IOException) {
            emit(UserPreview.Error(message = e.localizedMessage))
        }
    }
}

sealed class UserPreview(val data: UserPreviewModel? = null, val message: String? = null) {
    object Loading: UserPreview()
    class Success(data: UserPreviewModel?): UserPreview(data = data)
    class Error(message: String?): UserPreview(message = message)
}

