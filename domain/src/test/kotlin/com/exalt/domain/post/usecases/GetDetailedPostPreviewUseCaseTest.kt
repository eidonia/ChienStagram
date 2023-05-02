package com.exalt.domain.post.usecases

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.post.repositories.DetailedPostPreviewRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.util.UUID

class GetDetailedPostPreviewUseCaseTest {
    private val detailedPostPreviewRepository: DetailedPostPreviewRepository = mockk()
    private val getDetailedPostPreviewUseCase = GetDetailedPostPreviewUseCase(detailedPostPreviewRepository)

    @Test
    fun `Given repository returns full comment list, When use case is invoke, returns Loading State`() = runTest{
        val postId = UUID.randomUUID().toString()

        val actualPost = getDetailedPostPreviewUseCase.invoke(postId)

        Assert.assertEquals(actualPost.first(), PostPreview.Loading)
    }

    @Test
    fun `Given repository returns full comment list, When use case is invoke, returns full list`() = runTest{
        val postId = UUID.randomUUID().toString()
        val expectedPost = DomainModelFactory.getDefaultDetailedPostPreviewModel(postId)

        coEvery { detailedPostPreviewRepository.getPost(postId) } returns expectedPost

        val actualCommentList = getDetailedPostPreviewUseCase.invoke(postId).collect()

        val expectedFlow = flow {
            emit(PostPreview.Loading)
            emit(PostPreview.Success(expectedPost))
        }.collect()

        Assert.assertEquals(actualCommentList, expectedFlow)
    }
}