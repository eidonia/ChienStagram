package com.exalt.home.mappers

import com.exalt.domain.home.models.PostPreviewModel
import com.exalt.home.viewobjects.PostVO
import javax.inject.Inject

class PostPreviewMapper @Inject constructor() {
    fun toListPostVO(postPreviewModels: List<PostPreviewModel>) =
        postPreviewModels.map { toPostVO(it) }

    private fun toPostVO(postPreviewModel: PostPreviewModel) = PostVO(
            id = postPreviewModel.id,
            text = postPreviewModel.text,
            imageUri = postPreviewModel.imageUrl,
            publishDate = postPreviewModel.publishDate,
            ownerId = postPreviewModel.owner.id,
            ownerName = postPreviewModel.owner.name,
            ownerPictureUri = postPreviewModel.owner.pictureUrl
        )
}