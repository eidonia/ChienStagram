package com.exalt.post.mappers

import com.exalt.domain.post.models.DetailedPostPreviewModel
import com.exalt.post.viewobjects.DetailedPost
import javax.inject.Inject

class PostPreviewMapper @Inject constructor(){

    fun toPost(detailedPostPreviewModel: DetailedPostPreviewModel) = DetailedPost(
        id = detailedPostPreviewModel.id,
        text = detailedPostPreviewModel.text,
        imageUri = detailedPostPreviewModel.imageUrl,
        publishDate = detailedPostPreviewModel.publishDate,
        ownerId = detailedPostPreviewModel.owner.id,
        ownerName = detailedPostPreviewModel.owner.name,
        ownerPictureUri = detailedPostPreviewModel.owner.pictureUrl,
        tags = detailedPostPreviewModel.tags,
        likes = detailedPostPreviewModel.likes
    )
}