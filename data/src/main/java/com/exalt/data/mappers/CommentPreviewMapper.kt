package com.exalt.data.mappers

import com.exalt.api.models.CommentPreviewDTO
import com.exalt.domain.post.models.CommentPreviewModel
import javax.inject.Inject

class CommentPreviewMapper @Inject constructor(
    private val ownerPreviewMapper: OwnerPreviewMapper
) {
    fun fromListDto(commentsPreviews: List<CommentPreviewDTO>): List<CommentPreviewModel> =
        commentsPreviews.map { fromDto(it) }

    private fun fromDto(commentPreviewDTO: CommentPreviewDTO) = CommentPreviewModel(
        id = commentPreviewDTO.id,
        message = commentPreviewDTO.message,
        owner = ownerPreviewMapper.fromDto(commentPreviewDTO.owner),
        post = commentPreviewDTO.post,
        publishDate = commentPreviewDTO.publishDate
    )
}