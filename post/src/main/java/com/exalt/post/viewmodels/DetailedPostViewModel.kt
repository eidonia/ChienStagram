package com.exalt.post.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exalt.domain.post.usecases.CommentsPreview
import com.exalt.domain.post.usecases.GetCommentsPreviewUseCase
import com.exalt.domain.post.usecases.GetDetailedPostPreviewUseCase
import com.exalt.domain.post.usecases.PostPreview
import com.exalt.post.mappers.CommentPreviewMapper
import com.exalt.post.mappers.PostPreviewMapper
import com.exalt.post.viewobjects.CommentFlow
import com.exalt.post.viewobjects.PostFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailedPostViewModel @Inject constructor(
    private val getDetailedPostPreviewUseCase: GetDetailedPostPreviewUseCase,
    private val getCommentsPreviewUseCase: GetCommentsPreviewUseCase,
    private val postPreviewMapper: PostPreviewMapper,
    private val commentPreviewMapper: CommentPreviewMapper
): ViewModel() {

    val postState: MutableLiveData<PostFlow> by lazy {
        MutableLiveData<PostFlow>()
    }

    val commentState: MutableLiveData<CommentFlow> by lazy {
        MutableLiveData<CommentFlow>()
    }

    fun getPost(id: String) =
        getDetailedPostPreviewUseCase(id).onEach { result ->
            when (result) {
                is PostPreview.Loading -> {
                    postState.value = PostFlow(
                        isLoading = true
                    )
                }
                is PostPreview.Success -> {
                    postState.value = PostFlow(
                     post = postPreviewMapper.toPost(result.data!!)
                    )
                }
                is PostPreview.Error -> {
                    postState.value = PostFlow(
                        message = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)

    fun getComment(id: String) =
        getCommentsPreviewUseCase(id).onEach { result ->
            when(result) {
                is CommentsPreview.Loading -> {
                    commentState.value = CommentFlow(
                        isLoading = true
                    )
                }
                is CommentsPreview.Success -> {
                    commentState.value = CommentFlow(
                        data = commentPreviewMapper.toListComment(result.data!!)
                    )
                }
                is CommentsPreview.Error -> {
                    commentState.value = CommentFlow(
                        message = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)

}