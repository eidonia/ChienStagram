package com.exalt.data.repositories

import com.exalt.api.services.PostService
import com.exalt.data.mappers.UserPreviewMapper
import com.exalt.domain.profile.models.UserPreviewModel
import com.exalt.domain.profile.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val userPreviewMapper: UserPreviewMapper
): ProfileRepository {
    override suspend fun getUser(userId: String): UserPreviewModel? =
        postService.getUser(userId).let { response ->
            if (response.isSuccessful) {
                userPreviewMapper.fromDto(response.body()!!)
            } else {
                null
            }
        }
}