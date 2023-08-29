package com.exalt.data.mappers

import com.exalt.data.ModelDataFactory
import com.exalt.domain.home.models.DomainModelFactory
import io.mockk.every
import org.junit.Assert
import org.junit.Test

class UserPreviewMapperTest {
    private val dateMappers = DateMappers()
    private val userPreviewMapper = UserPreviewMapper(dateMappers)

    @Test
    fun `given a user preview DTO, when user mapper called return a user preview model`() {
        val getUserPreviewDTO = ModelDataFactory.getUserPreviewDTO()

        val mappedUserPreviewModel = userPreviewMapper.fromDto(getUserPreviewDTO)
        val expectedUserPreviewModel = DomainModelFactory.getDefaultUserPreviewModel()

        Assert.assertEquals(expectedUserPreviewModel, mappedUserPreviewModel)
    }
}