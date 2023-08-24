package com.exalt.profile.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exalt.domain.profile.usecases.GetProfilePreviewUseCase
import com.exalt.domain.profile.usecases.UserPreview
import com.exalt.profile.mappers.UserPreviewMapper
import com.exalt.profile.viewobjects.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfilePreviewUseCase: GetProfilePreviewUseCase,
    private val userPreviewMapper: UserPreviewMapper
): ViewModel() {

    val userState: MutableLiveData<UserState> by lazy { MutableLiveData<UserState>() }

    fun getUser(userId: String) =
        getProfilePreviewUseCase(userId).onEach { result ->
            when(result) {
                is UserPreview.Loading -> {
                    userState.value = UserState(isLoading = true)
                }
                is UserPreview.Success -> {
                    userState.value = UserState(data = userPreviewMapper.toUser(result.data!!))
                }
                is UserPreview.Error -> {
                    userState.value = UserState(message = result.message!!)
                }
            }
        }.launchIn(viewModelScope)

}