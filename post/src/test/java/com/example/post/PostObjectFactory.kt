package com.example.post

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.post.viewobjects.Comment
import com.exalt.post.viewobjects.DetailedPost

object PostObjectFactory {
    fun getDefaultDetailedPost(id: String = DomainModelFactory.POST_ID) = DetailedPost(
        id = id,
        text = DomainModelFactory.POST_TEXT,
        imageUri = DomainModelFactory.POST_IMAGE_URL,
        publishDate = DomainModelFactory.POST_PUBLISH_DATE_MAP,
        ownerId = DomainModelFactory.OWNER_ID,
        ownerName = "${DomainModelFactory.OWNER_FIRST_NAME} ${DomainModelFactory.OWNER_LAST_NAME}",
        ownerPictureUri = DomainModelFactory.OWNER_PICTURE_URL,
        likes = 23,
        tags = listOf("tag1", "tag2", "tag3"),
    )

    fun getDefaultComment(id: String = DomainModelFactory.COMMENT_ID) = Comment (
        id = id,
        message = DomainModelFactory.COMMENT_MESSAGE,
        ownerId = DomainModelFactory.OWNER_ID,
        ownerName = "${DomainModelFactory.OWNER_FIRST_NAME} ${DomainModelFactory.OWNER_LAST_NAME}",
        ownerPictureUrl = DomainModelFactory.OWNER_PICTURE_URL,
        post = DomainModelFactory.COMMENT_POST,
        publishDate = DomainModelFactory.COMMENT_PUBLISH_DATE
    )
}