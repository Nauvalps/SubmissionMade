package com.bangkit.submissionmade1.core.data.source.remote.network

import com.bangkit.submissionmade1.core.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String = "e28112c9571447c9ba7497acec0ebfba"
    ) : NewsResponse

    @GET("top-headlines")
    suspend fun getHealthNews(
        @Query("country") country: String = "id",
        @Query("category") category: String = "health",
        @Query("apiKey") apiKey: String = "e28112c9571447c9ba7497acec0ebfba"
    ) : NewsResponse

    @GET("top-headlines")
    suspend fun getSportsNews(
        @Query("country") country: String = "id",
        @Query("category") category: String = "sports",
        @Query("apiKey") apiKey: String = "e28112c9571447c9ba7497acec0ebfba"
    ) : NewsResponse
}