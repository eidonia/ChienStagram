package com.exalt.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.exalt.domain.home.usecases.GetPostPreviewsUseCase
import com.exalt.home.mappers.PostPreviewMapper
import com.exalt.home.viewobjects.PostVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostPreviewsUseCase: GetPostPreviewsUseCase,
    private val postPreviewMapper: PostPreviewMapper,
) : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    val posts: LiveData<List<PostVO>> = liveData {
        emit(
            postPreviewMapper.toListPostVO(getPostPreviewsUseCase.invoke())
        )
        _isLoading.value = false
    }
}