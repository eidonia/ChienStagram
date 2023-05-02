package com.example.post.mappers

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.post.models.CommentPreviewModel
import com.example.post.PostObjectFactory.getDefaultComment
import org.junit.Assert
import org.junit.Test
import java.util.UUID

class CommentPreviewMapperTest {
    private val commentPreviewMapper = com.exalt.post.mappers.CommentPreviewMapper()

    @Test
    fun `Given list of Comment Preview Model, when mapper is called, Then returns list of Comments`() {
        val randomUuids = List(10) { UUID.randomUUID().toString() }

        val actualComments = commentPreviewMapper.toListComment(randomUuids.map { DomainModelFactory.getDefaultCommentPreviewModel(it)})
        val expectedComments = randomUuids.map { getDefaultComment(it) }

        // Then
        Assert.assertEquals(expectedComments, actualComments)
    }

    @Test
    fun `Given empty list of Post Preview Model, when mapper is called, Then returns empty list of PostVO`() {
        // Given
        val commentPreviewModels = emptyList<CommentPreviewModel>()

        // When
        val actualComments = commentPreviewMapper.toListComment(commentPreviewModels)

        // Then
        Assert.assertTrue(actualComments.isEmpty())
    }
}