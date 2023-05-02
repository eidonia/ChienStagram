package com.exalt.home.mappers

import com.exalt.domain.home.models.DomainModelFactory.getDefaultPostPreviewModel
import com.exalt.domain.home.models.PostPreviewModel
import com.exalt.home.HomeVOFactory.getDefaultPostVO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.UUID

class PostPreviewMapperTest {
    private val postPreviewMapper = PostPreviewMapper()

    @Test
    fun `Given list of Post Preview Model, when mapper is called, Then returns list of PostVO`() {
        // Given
        val randomUuids = List(23) { UUID.randomUUID().toString() }

        // When
        val actualPostVO = postPreviewMapper.toListPostVO(randomUuids.map { getDefaultPostPreviewModel(it) })
        val expectedPostVO = randomUuids.map { getDefaultPostVO(it) }

        // Then
        assertEquals(expectedPostVO, actualPostVO)
    }

    @Test
    fun `Given empty list of Post Preview Model, when mapper is called, Then returns empty list of PostVO`() {
        // Given
        val postPreviewModels = emptyList<PostPreviewModel>()

        // When
        val actualPostVO = postPreviewMapper.toListPostVO(postPreviewModels)

        // Then
        assertTrue(actualPostVO.isEmpty())
    }
}