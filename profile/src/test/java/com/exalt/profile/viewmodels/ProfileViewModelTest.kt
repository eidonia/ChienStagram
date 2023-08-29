package com.exalt.profile.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.post.usecases.PostPreview
import com.exalt.domain.profile.models.UserPreviewModel
import com.exalt.domain.profile.usecases.GetProfilePreviewUseCase
import com.exalt.domain.profile.usecases.UserPreview
import com.exalt.profile.mappers.UserPreviewMapper
import com.exalt.profile.viewmodels.UserObjectFactory.getDefaultUser
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.UUID

class ProfileViewModelTest {
    private val getProfilePreviewUseCase: GetProfilePreviewUseCase = mockk()
    private val userPreviewMapper: UserPreviewMapper = UserPreviewMapper()
    private val profileViewModel = ProfileViewModel(getProfilePreviewUseCase, userPreviewMapper)
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
    fun `given a UserPreviewModel in usecase, return a UserState with User`() = runTest {
        val userId = UUID.randomUUID().toString()
        val expectedUser = getDefaultUser(userId)
        val userPreviewModel = DomainModelFactory.getDefaultUserPreviewModel()
        val expectedFlow = flow {
            emit(UserPreview.Loading)
            emit(UserPreview.Success(userPreviewModel))
        }
        every { getProfilePreviewUseCase.invoke(any()) } returns expectedFlow
        profileViewModel.getUser(userId)
        val actualUser = profileViewModel.userState.value

        assertEquals(expectedUser, actualUser?.data)

    }

    @Test
    fun `given a UserPreviewModel in usecase, return an error`() = runTest {
        val userId = UUID.randomUUID().toString()
        val expectedMessage = "repository error: profile is null"
        val expectedFlow = flow {
            emit(UserPreview.Loading)
            emit(UserPreview.Error("repository error: profile is null"))
        }
        every { getProfilePreviewUseCase.invoke(any()) } returns expectedFlow
        profileViewModel.getUser(userId)
        val actualUser = profileViewModel.userState.value

        assertEquals(expectedMessage, actualUser?.message)

    }
}