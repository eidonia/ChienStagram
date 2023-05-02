package com.exalt.api

import com.exalt.api.services.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DummyApi {
    companion object {
        const val APP_ID = BuildConfig.API_KEY
    }

    val postService: PostService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyapi.io/data/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        postService = retrofit.create(PostService::class.java)
    }

}