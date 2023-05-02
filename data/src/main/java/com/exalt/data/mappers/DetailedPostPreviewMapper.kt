package com.exalt.data.mappers

import com.exalt.api.models.PostPreviewDTO
import com.exalt.domain.post.models.DetailedPostPreviewModel
import javax.inject.Inject

class DetailedPostPreviewMapper @Inject constructor(
    private val ownerPreviewMapper: OwnerPreviewMapper,
    private val dateMappers: DateMappers
) {

    fun fromDto(postPreview: PostPreviewDTO) = DetailedPostPreviewModel(
        id = postPreview.id,
        text = postPreview.text,
        imageUrl = postPreview.image,
        publishDate = dateMappers.toDate(postPreview.publishDate),
        owner = ownerPreviewMapper.fromDto(postPreview.owner),
        tags = postPreview.tags,
        likes = postPreview.likes
    )
}