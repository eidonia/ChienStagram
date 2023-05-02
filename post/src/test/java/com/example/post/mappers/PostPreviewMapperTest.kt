package com.example.post.mappers

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.post.mappers.PostPreviewMapper
import com.example.post.PostObjectFactory.getDefaultDetailedPost
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID

class PostPreviewMapperTest {
    private val postPreviewMapper = PostPreviewMapper()

    @Test
    fun `Given a DetailedPost Preview Model, when mapper is called, Then returns a Post`() {

        val randomUuid = UUID.randomUUID().toString()

        val actualPost = postPreviewMapper.toPost(DomainModelFactory.getDefaultDetailedPostPreviewModel(randomUuid))
        val expectedPost = getDefaultDetailedPost(randomUuid)

        assertEquals(actualPost, expectedPost)
    }
}