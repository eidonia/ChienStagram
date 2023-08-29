package com.exalt.data

import com.exalt.api.models.CommentPreviewDTO
import com.exalt.api.models.Location
import com.exalt.api.models.PostPreviewDTO
import com.exalt.api.models.UserPreviewDTO
import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.home.models.DomainModelFactory.COMMENT_ID
import com.exalt.domain.home.models.DomainModelFactory.COMMENT_MESSAGE
import com.exalt.domain.home.models.DomainModelFactory.COMMENT_POST
import com.exalt.domain.home.models.DomainModelFactory.COMMENT_PUBLISH_DATE
import com.exalt.domain.home.models.DomainModelFactory.OWNER_FIRST_NAME
import com.exalt.domain.home.models.DomainModelFactory.OWNER_ID
import com.exalt.domain.home.models.DomainModelFactory.OWNER_LAST_NAME
import com.exalt.domain.home.models.DomainModelFactory.OWNER_PICTURE_URL
import com.exalt.domain.home.models.DomainModelFactory.POST_ID

object ModelDataFactory {
    fun getPostPreviewDTO(id: String = POST_ID) = PostPreviewDTO(
        id = id,
        text = DomainModelFactory.POST_TEXT,
        image = DomainModelFactory.POST_IMAGE_URL,
        likes = 23,
        tags = listOf("tag1", "tag2", "tag3"),
        publishDate = DomainModelFactory.POST_PUBLISH_DATE,
        owner = getUserPreviewDTO(),
    )

    fun getUserPreviewDTO(id: String = OWNER_ID) =
        UserPreviewDTO(
            id = OWNER_ID,
            dateOfBirth = DomainModelFactory.OWNER_DATE_BIRTH,
            email = DomainModelFactory.OWNER_EMAIL,
            firstName = OWNER_FIRST_NAME,
            gender = DomainModelFactory.OWNER_GENDER,
            lastName = OWNER_LAST_NAME,
            location = Location(
                city = DomainModelFactory.OWNER_LOCATION_CITY,
                country = DomainModelFactory.OWNER_LOCATION_COUNTRY,
                state = DomainModelFactory.OWNER_LOCATION_STATE,
                street = DomainModelFactory.OWNER_LOCATION_STREET
            ),
            phone = DomainModelFactory.OWNER_PHONE,
            picture = OWNER_PICTURE_URL
        )

    fun getCommentPreviewDTO(id: String = COMMENT_ID) = CommentPreviewDTO(
        id = id,
        message = COMMENT_MESSAGE,
        owner = getUserPreviewDTO(),
        post = COMMENT_POST,
        publishDate = COMMENT_PUBLISH_DATE

    )
}