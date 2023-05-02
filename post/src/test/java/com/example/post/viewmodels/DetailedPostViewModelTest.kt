package com.example.post.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exalt.domain.home.models.DomainModelFactory.getDefaultCommentPreviewModel
import com.exalt.domain.home.models.DomainModelFactory.getDefaultDetailedPostPreviewModel
import com.exalt.domain.post.usecases.CommentsPreview
import com.exalt.domain.post.usecases.GetCommentsPreviewUseCase
import com.exalt.domain.post.usecases.GetDetailedPostPreviewUseCase
import com.exalt.domain.post.usecases.PostPreview
import com.exalt.post.mappers.CommentPreviewMapper
import com.exalt.post.mappers.PostPreviewMapper
import com.exalt.post.viewmodels.DetailedPostViewModel
import com.example.post.PostObjectFactory.getDefaultComment
import com.example.post.PostObjectFactory.getDefaultDetailedPost
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.UUID

@OptIn(ExperimentalCoroutinesApi::class)
class DetailedPostViewModelTest {
    private val getDetailedPostPreviewUseCase: GetDetailedPostPreviewUseCase = mockk()
    private val getCommentsPreviewUseCase: GetCommentsPreviewUseCase = mockk()
    private val postPreviewMapper: PostPreviewMapper = PostPreviewMapper()
    private val commentPreviewMapper: CommentPreviewMapper = CommentPreviewMapper()
    private val detailedPostViewModel = DetailedPostViewModel(getDetailedPostPreviewUseCase, getCommentsPreviewUseCase, postPreviewMapper, commentPreviewMapper)
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given a DetailedPostPreview in usecase, return PostFlow with Post`() = runTest {
        val postId = UUID.randomUUID().toString()
        val expectedPost = getDefaultDetailedPost(postId)
        val detailedPostPreviewModel = getDefaultDetailedPostPreviewModel(postId)
        val expectedFlow = flow {
            emit(PostPreview.Loading)
            emit(PostPreview.Success(detailedPostPreviewModel))
        }
        every { getDetailedPostPreviewUseCase.invoke(any()) } returns expectedFlow
        detailedPostViewModel.getPost(postId)
        val actualPost = detailedPostViewModel.postState.value

        assertEquals(expectedPost, actualPost?.post)
    }

    @Test
    fun `Given a list of CommentPreviewModel in usecase, return CommentFlow with a list of Comment`() = runTest {
        val postId = UUID.randomUUID().toString()
        val expectedComments = List(10) { getDefaultComment() }
        val commentPreviewModels = List(10) { getDefaultCommentPreviewModel() }
        val expectedFlow = flow {
            emit(CommentsPreview.Loading)
            emit(CommentsPreview.Success(commentPreviewModels))
        }
        every { getCommentsPreviewUseCase.invoke(any()) } returns expectedFlow
        detailedPostViewModel.getComment(postId)
        val actualComments = detailedPostViewModel.commentState.value

        assertEquals(expectedComments, actualComments?.data)
    }
}