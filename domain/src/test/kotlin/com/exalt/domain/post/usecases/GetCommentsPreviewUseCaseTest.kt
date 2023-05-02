package com.exalt.domain.post.usecases

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.post.repositories.DetailedPostPreviewRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID

class GetCommentsPreviewUseCaseTest {
    private val detailedPostPreviewRepository: DetailedPostPreviewRepository = mockk()
    private val getCommentsPreviewUseCase = GetCommentsPreviewUseCase(detailedPostPreviewRepository)

    @Test
    fun `Given repository returns full comment list, When use case is invoke, returns Loading State`() = runTest{
        val postId = UUID.randomUUID().toString()

        val actualCommentList = getCommentsPreviewUseCase.invoke(postId)

        assertEquals(actualCommentList.first(), CommentsPreview.Loading)
    }

    @Test
    fun `Given repository returns full comment list, When use case is invoke, returns full list`() = runTest{
        val postId = UUID.randomUUID().toString()
        val expectedCommentsList = List(10) {
            DomainModelFactory.getDefaultCommentPreviewModel(UUID.randomUUID().toString())
        }

        coEvery { detailedPostPreviewRepository.getComment(postId) } returns expectedCommentsList

        val actualCommentList = getCommentsPreviewUseCase.invoke(postId).collect()

        val expectedFlow = flow {
            emit(CommentsPreview.Loading)
            emit(CommentsPreview.Success(expectedCommentsList))
        }.collect()

        assertEquals(actualCommentList, expectedFlow)
    }
}