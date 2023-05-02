package com.exalt.data.mappers

import com.exalt.api.models.CommentPreviewDTO
import com.exalt.data.ModelDataFactory
import com.exalt.domain.home.models.DomainModelFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import java.util.UUID

class CommentPreviewMapperTest {

    private val ownerPreviewMapper: OwnerPreviewMapper = mockk()
    private val commentPreviewMapper = CommentPreviewMapper(ownerPreviewMapper)

    @Test
    fun `Given list of preview comment, wrapper is called and returns list of comment preview model`() {

        val randomUuids = List(10) { UUID.randomUUID().toString() }
        val commentPreviewDTOs = randomUuids.map { ModelDataFactory.getCommentPreviewDTO(it) }
        every { ownerPreviewMapper.fromDto(any()) } returns DomainModelFactory.getDefaultOwnerPreviewModel()

        val actualCommentPreviewModels = commentPreviewMapper.fromListDto(commentPreviewDTOs)
        val expectedCommentPreviewModels = randomUuids.map {
            DomainModelFactory.getDefaultCommentPreviewModel(it)}

        Assert.assertEquals(expectedCommentPreviewModels, actualCommentPreviewModels)
    }

    @Test
    fun `Given empty list of comment preview DTO, When mapper is called, Then returns empty list of post preview model`() {
        // Given
        val commentPreviewDTOs = emptyList<CommentPreviewDTO>()

        // When
        val commentPreviewModel = commentPreviewMapper.fromListDto(commentPreviewDTOs)

        // Then
        Assert.assertTrue(commentPreviewModel.isEmpty())
    }

}