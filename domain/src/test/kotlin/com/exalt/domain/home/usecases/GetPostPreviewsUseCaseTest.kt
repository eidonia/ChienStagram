package com.exalt.domain.home.usecases

import com.exalt.domain.home.models.DomainModelFactory.getDefaultPostPreviewModel
import com.exalt.domain.home.repositories.PostPreviewRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.UUID

class GetPostPreviewsUseCaseTest {
    private val postPreviewRepository: PostPreviewRepository = mockk()
    private val getPostPreviewsUseCase = GetPostPreviewsUseCase(postPreviewRepository)

    @Test
    fun `Given repository returns full list, When GetPostPreviewsUseCase is invoked, Then returns full list`() = runTest {
        // Given
        val expectedPostsList = List(23) {
            getDefaultPostPreviewModel(UUID.randomUUID().toString())
        }

        coEvery { postPreviewRepository.getPosts(0u) } returns expectedPostsList

        // When
        val actualPostsList = getPostPreviewsUseCase.invoke()

        // Then
        assertEquals(expectedPostsList, actualPostsList)
    }

    @Test
    fun `Given repository returns empty list, When GetPostPreviewsUseCase is invoked, Then returns empty list`() = runTest {
        // Given
        coEvery { postPreviewRepository.getPosts(23u) } returns emptyList()

        // When
        val postsList = getPostPreviewsUseCase.invoke()

        // Then
        assertTrue(postsList.isEmpty())
    }

    @Test
    fun `Given repository throws exception, When GetPostPreviewsUseCase is invoked, Then returns empty list`() = runTest {
        // Given
        coEvery { postPreviewRepository.getPosts(23u) } throws NullPointerException()

        // When
        val postsList = getPostPreviewsUseCase.invoke()

        // Then
        assertTrue(postsList.isEmpty())
    }
}