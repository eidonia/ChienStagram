package com.exalt.api.services

import com.exalt.api.DummyApi
import com.exalt.api.models.CommentPreviewDTO
import com.exalt.api.models.Page
import com.exalt.api.models.PostPreviewDTO
import com.exalt.api.models.UserPreviewDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("post?limit=23")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getPosts(@Query("page") page: UInt): Response<Page<PostPreviewDTO>>

    @GET("post/{id}")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getPost(@Path("id") id: String): Response<PostPreviewDTO>

    @GET("post/{id}/comment")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getPostComment(@Path("id") id: String): Response<Page<CommentPreviewDTO>>

    @GET("user/{id}")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getUser(@Path("id") id: String): Response<UserPreviewDTO>
}