package com.exalt.data.repositories

import com.exalt.api.models.Page
import com.exalt.api.services.PostService
import com.exalt.data.ModelDataFactory.getPostPreviewDTO
import com.exalt.data.mappers.PostPreviewMapper
import com.exalt.domain.home.models.DomainModelFactory.getDefaultPostPreviewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response
import java.util.UUID

class PostPreviewRepositoryImplTest {
    private val postService: PostService = mockk()
    private val postPreviewMapper: PostPreviewMapper = mockk()
    private val postPreviewRepositoryImpl = PostPreviewRepositoryImpl(postService, postPreviewMapper)

    @Test
    fun `Given a successful response with posts on page 23, When getting posts through repository, Then returns list of PostPreview`() = runTest {
        // Given
        val page = 23u
        val randomUuids = List(50) { UUID.randomUUID().toString() }
        val postPreviewDTOs = randomUuids.map { getPostPreviewDTO(it) }
        val expectedPostPreviews = randomUuids.map { getDefaultPostPreviewModel(it) }
        coEvery { postService.getPosts(page) } returns Response.success(Page(data = postPreviewDTOs, total = page))
        coEvery { postPreviewMapper.fromListDto(postPreviewDTOs) } returns expectedPostPreviews

        // When
        val actualPostPreviews = postPreviewRepositoryImpl.getPosts(page)

        // Then
        assertEquals(expectedPostPreviews, actualPostPreviews)
    }

    @Test
    fun `Given a successful response with no post on page 0, When getting posts through repository, Then returns an empty list`() = runTest {
        // Given
        val page = 0u
        coEvery { postService.getPosts(page) } returns Response.success(Page(data = emptyList(), total = page))
        coEvery { postPreviewMapper.fromListDto(emptyList()) } returns emptyList()

        // When
        val postPreviews = postPreviewRepositoryImpl.getPosts(page)

        // Then
        assertTrue(postPreviews.isEmpty())
    }

    @Test
    fun `Given an error response, When getting posts through repository, Then returns an empty list`() = runTest {
        // Given
        val page = 0u
        coEvery { postService.getPosts(page) } returns Response.error(404, ResponseBody.create(MediaType.get("application/json"), "HTTP NOT FOUND"))

        // When
        val postPreviews = postPreviewRepositoryImpl.getPosts(page)

        // Then
        assertTrue(postPreviews.isEmpty())
    }
}