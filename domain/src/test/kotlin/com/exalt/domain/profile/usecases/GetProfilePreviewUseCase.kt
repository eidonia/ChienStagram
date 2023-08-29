package com.exalt.domain.profile.usecases

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.domain.profile.models.UserPreviewModel
import com.exalt.domain.profile.repositories.ProfileRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.util.UUID

class GetProfilePreviewUseCaseTest {

    private val profileRepository: ProfileRepository = mockk()
    private val getProfilePreviewUseCase = GetProfilePreviewUseCase(profileRepository)

    @Test
    fun `given repository returns profile preview, when use case is invoke, returns loading state`() = runTest {
    val userId = UUID.randomUUID().toString()
        val profilePreview = getProfilePreviewUseCase.invoke(userId)

        Assert.assertEquals(profilePreview.first(), UserPreview.Loading)
    }

    @Test
    fun `given repository returns profile preview, when use case is invoke, returns profile`() = runTest {
        val userId = UUID.randomUUID().toString()
        val expectedProfilePreview = DomainModelFactory.getDefaultUserPreviewModel()
        var actualProfilePreview: UserPreviewModel? = null

        coEvery { profileRepository.getUser(userId) } returns expectedProfilePreview

        getProfilePreviewUseCase.invoke(userId).onEach { result ->
            when(result) {
                is UserPreview.Loading -> {}
                is UserPreview.Success -> actualProfilePreview = result.data
                is UserPreview.Error -> {}
            }
        }.collect()

        Assert.assertEquals(expectedProfilePreview, actualProfilePreview)
    }

    @Test
    fun `given repository returns error, when use case is invoke, returns Error State`() = runTest {
        val userId = UUID.randomUUID().toString()
        val expectedProfilePreview = null
        val expectedMessage = "repository error: profile is null"
        var message = ""


        coEvery { profileRepository.getUser(userId) } returns expectedProfilePreview

        getProfilePreviewUseCase.invoke(userId).onEach { result ->
            when(result) {
                is UserPreview.Loading -> {}
                is UserPreview.Success -> {}
                is UserPreview.Error -> message = result.message
            }
        }.collect()

        Assert.assertEquals(expectedMessage, message)
    }
}