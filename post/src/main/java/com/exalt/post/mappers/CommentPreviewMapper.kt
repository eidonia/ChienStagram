package com.exalt.post.mappers

import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.post.viewobjects.Comment
import javax.inject.Inject

class CommentPreviewMapper @Inject constructor() {

    fun toListComment(commentPreviewModels: List<CommentPreviewModel>) =
        commentPreviewModels.map { toPostVO(it) }

    private fun toPostVO(commentPreviewModel: CommentPreviewModel) = Comment(
        id = commentPreviewModel.id,
        message = commentPreviewModel.message,
        ownerId = commentPreviewModel.owner.id,
        ownerName = commentPreviewModel.owner.name,
        ownerPictureUrl = commentPreviewModel.owner.pictureUrl,
        post = commentPreviewModel.post,
        publishDate = commentPreviewModel.publishDate
    )

}