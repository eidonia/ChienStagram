package com.exalt.data.mappers

import com.exalt.data.ModelDataFactory
import com.exalt.domain.home.models.DomainModelFactory.getDefaultDetailedPostPreviewModel
import com.exalt.domain.home.models.DomainModelFactory.getDefaultOwnerPreviewModel
import org.junit.Assert.assertEquals
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import java.util.UUID

class DetailedPostPreviewMapperTest {
    private val ownerPreviewMapper: OwnerPreviewMapper = mockk()
    private val dateMappers: DateMappers = mockk()
    private val detailedPostPreviewMapper = DetailedPostPreviewMapper(ownerPreviewMapper, dateMappers)

    @Test
    fun `Given a detailedPostPreviewDTO wrapper called and returns detailedPostPreviewModel`() {
        val randomUUID = UUID.randomUUID().toString()
        val detailedPostPreviewDTO = ModelDataFactory.getPostPreviewDTO(randomUUID)
        every { ownerPreviewMapper.fromDto(any()) } returns getDefaultOwnerPreviewModel()
        every { dateMappers.toDate(any()) } returns "24 mai 2020"

        val actualDetailedPostPreviewModel = detailedPostPreviewMapper.fromDto(detailedPostPreviewDTO)
        val expectedDetailPostPreviewModel = getDefaultDetailedPostPreviewModel(randomUUID)

        assertEquals(expectedDetailPostPreviewModel, actualDetailedPostPreviewModel)
    }
}