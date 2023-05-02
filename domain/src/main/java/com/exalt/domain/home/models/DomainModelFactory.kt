package com.exalt.domain.home.models

import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.domain.post.models.DetailedPostPreviewModel

object DomainModelFactory {
    const val POST_ID = "POST_ID"
    const val POST_TEXT = "POST_TEXT"
    const val POST_IMAGE_URL = "POST_IMAGE_URL"
    const val POST_PUBLISH_DATE = "2020-05-24T14:53:17.598Z"
    const val POST_PUBLISH_DATE_MAP = "24 mai 2020"

    const val OWNER_ID = "OWNER_ID"
    const val OWNER_TITLE = "OWNER_TITLE"
    const val OWNER_FIRST_NAME = "OWNER_FIRST_NAME"
    const val OWNER_LAST_NAME = "OWNER_LAST_NAME"
    const val OWNER_PICTURE_URL = "OWNER_PICTURE_URL"

    const val COMMENT_ID = "COMMENT_ID"
    const val COMMENT_MESSAGE = "COMMENT_MESSAGE"
    const val COMMENT_POST = "COMMENT_POST"
    const val COMMENT_PUBLISH_DATE = "COMMENT_PUBLISH_DATE"

    fun getDefaultPostPreviewModel(
        id: String = POST_ID
    ) = PostPreviewModel(
        id = id,
        text = POST_TEXT,
        imageUrl = POST_IMAGE_URL,
        publishDate = POST_PUBLISH_DATE,
        owner = getDefaultOwnerPreviewModel(),
    )

    fun getDefaultOwnerPreviewModel() = OwnerPreviewModel(
        id = OWNER_ID, name = "$OWNER_FIRST_NAME $OWNER_LAST_NAME", pictureUrl = OWNER_PICTURE_URL
    )

    fun getDefaultDetailedPostPreviewModel(
        id: String = POST_ID
    ) = DetailedPostPreviewModel(
        id = id,
        text = POST_TEXT,
        imageUrl = POST_IMAGE_URL,
        publishDate = POST_PUBLISH_DATE_MAP,
        owner = getDefaultOwnerPreviewModel(),
        likes = 23,
        tags = listOf("tag1", "tag2", "tag3"),
    )

    fun getDefaultCommentPreviewModel(id: String = COMMENT_ID) = CommentPreviewModel (
        id = id,
        message = COMMENT_MESSAGE,
        owner = getDefaultOwnerPreviewModel(),
        post = COMMENT_POST,
        publishDate = COMMENT_PUBLISH_DATE
    )
}