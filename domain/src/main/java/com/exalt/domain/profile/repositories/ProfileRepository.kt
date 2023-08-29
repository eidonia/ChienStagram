package com.exalt.domain.profile.repositories

import com.exalt.domain.profile.models.UserPreviewModel

interface ProfileRepository {
    suspend fun getUser(userId: String): UserPreviewModel?
}