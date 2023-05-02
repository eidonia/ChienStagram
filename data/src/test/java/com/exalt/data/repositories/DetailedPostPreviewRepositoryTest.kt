package com.exalt.data.repositories

import com.exalt.api.models.Page
import com.exalt.api.services.PostService
import com.exalt.data.ModelDataFactory
import com.exalt.data.mappers.CommentPreviewMapper
import com.exalt.data.mappers.DetailedPostPreviewMapper
import com.exalt.domain.home.models.DomainModelFactory.getDefaultCommentPreviewModel
import com.exalt.domain.home.models.DomainModelFactory.getDefaultDetailedPostPreviewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response
import java.util.UUID

class DetailedPostPreviewRepositoryTest {
    private val postService: PostService = mockk()
    private val detailedPostPreviewMapper: DetailedPostPreviewMapper = mockk()
    private val commentPreviewMapper: CommentPreviewMapper = mockk()
    private val postPreviewRepositoryImpl = DetailedPostPreviewRepositoryImpl(postService, detailedPostPreviewMapper, commentPreviewMapper)

    @Test
    fun `Given a Successful response with 1 post and return a DetailedPostPreviewModel`() = runTest {
        val randomUUID = UUID.randomUUID().toString()
        val postPreviewDTO = ModelDataFactory.getPostPreviewDTO(randomUUID)
        val expectedDetailedPostPreview = getDefaultDetailedPostPreviewModel(randomUUID)
        coEvery { postService.getPost(randomUUID) } returns Response.success(postPreviewDTO)
        coEvery { detailedPostPreviewMapper.fromDto(postPreviewDTO) } returns expectedDetailedPostPreview

        val actualPostPreview = postPreviewRepositoryImpl.getPost(randomUUID)

        assertEquals(expectedDetailedPostPreview, actualPostPreview)
    }


    @Test
    fun `Given an Error for getPost() response`() = runTest {
        val randomUUID = UUID.randomUUID().toString()
        coEvery { postService.getPost(randomUUID) } returns Response.error(404, ResponseBody.create(
            MediaType.get("application/json"), "HTTP NOT FOUND"))

        val actualPostPreview = postPreviewRepositoryImpl.getPost(randomUUID)

        assertTrue(actualPostPreview == null)
    }

    @Test
    fun `Given a Successful response with comments and return list of CommentPreviewModel`() = runTest {

        val postUUID = UUID.randomUUID().toString()
        val randomUUID = List(10) {UUID.randomUUID().toString() }
        val commentPreviewDTO = randomUUID.map { ModelDataFactory.getCommentPreviewDTO(it) }
        val expectedCommentPreview = randomUUID.map { getDefaultCommentPreviewModel(it) }
        coEvery { postService.getPostComment(postUUID) } returns Response.success(Page(data = commentPreviewDTO, total = 0u))
        coEvery { commentPreviewMapper.fromListDto(commentPreviewDTO) } returns expectedCommentPreview

        val actualPostPreview = postPreviewRepositoryImpl.getComment(postUUID)

        assertEquals(expectedCommentPreview, actualPostPreview)
    }

    @Test
    fun `Given a Successful response with 0 comments and return list of CommentPreviewModel`() = runTest {

        val postUUID = UUID.randomUUID().toString()
        coEvery { postService.getPostComment(postUUID) } returns Response.success(Page(data = emptyList(), total = 0u))
        coEvery { commentPreviewMapper.fromListDto(emptyList()) } returns emptyList()

        val actualPostPreview = postPreviewRepositoryImpl.getComment(postUUID)

        assertTrue(actualPostPreview.isEmpty())
    }

    @Test
    fun `Given an Error for getCommentPost() response`() = runTest {

        val postUUID = UUID.randomUUID().toString()
        coEvery { postService.getPostComment(postUUID) } returns Response.error(404, ResponseBody.create(
            MediaType.get("application/json"), "HTTP NOT FOUND"))

        val actualPostPreview = postPreviewRepositoryImpl.getComment(postUUID)

        assertTrue(actualPostPreview.isEmpty())
    }
}