package com.exalt.data.mappers

import com.exalt.data.ModelDataFactory.getUserPreviewDTO
import com.exalt.domain.home.models.DomainModelFactory.getDefaultOwnerPreviewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class OwnerPreviewMapperTest {
    private val ownerPreviewMapper = OwnerPreviewMapper()

    @Test
    fun `Given user preview DTO, When mapper is called, Then returns owner preview model`() {
        // Given
        val userPreviewDTO = getUserPreviewDTO()

        // When
        val actualUserPreviewModel = ownerPreviewMapper.fromDto(userPreviewDTO)
        val expectedUserPreviewModel = getDefaultOwnerPreviewModel()

        // Then
        assertEquals(expectedUserPreviewModel, actualUserPreviewModel)
    }
}