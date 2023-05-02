package com.exalt.home

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.home.models.DomainModelFactory.OWNER_FIRST_NAME
import com.exalt.domain.home.models.DomainModelFactory.OWNER_ID
import com.exalt.domain.home.models.DomainModelFactory.OWNER_LAST_NAME
import com.exalt.domain.home.models.DomainModelFactory.OWNER_PICTURE_URL
import com.exalt.domain.home.models.DomainModelFactory.POST_ID
import com.exalt.domain.home.models.DomainModelFactory.POST_IMAGE_URL
import com.exalt.domain.home.models.DomainModelFactory.POST_PUBLISH_DATE
import com.exalt.domain.home.models.DomainModelFactory.POST_TEXT
import com.exalt.domain.post.models.CommentPreviewModel
import com.exalt.home.viewobjects.PostVO

object HomeVOFactory {
    fun getDefaultPostVO(id: String = POST_ID) = PostVO(
        id = id,
        text = POST_TEXT,
        imageUri = POST_IMAGE_URL,
        publishDate = POST_PUBLISH_DATE,
        ownerId = OWNER_ID,
        ownerName = "$OWNER_FIRST_NAME $OWNER_LAST_NAME",
        ownerPictureUri = OWNER_PICTURE_URL,
    )
}