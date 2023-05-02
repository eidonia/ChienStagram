package com.exalt.data.mappers

import com.exalt.api.models.PostPreviewDTO
import com.exalt.domain.home.models.PostPreviewModel
import javax.inject.Inject

class PostPreviewMapper @Inject constructor(
    private val ownerPreviewMapper: OwnerPreviewMapper,
) {
    fun fromListDto(postPreviews: List<PostPreviewDTO>): List<PostPreviewModel> =
        postPreviews.map { fromDto(it) }

    private fun fromDto(postPreview: PostPreviewDTO) = PostPreviewModel(
        id = postPreview.id,
        text = postPreview.text,
        imageUrl = postPreview.image,
        publishDate = postPreview.publishDate,
        owner = ownerPreviewMapper.fromDto(postPreview.owner)
    )
}