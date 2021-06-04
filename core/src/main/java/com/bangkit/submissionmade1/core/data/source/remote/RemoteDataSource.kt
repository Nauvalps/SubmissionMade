package com.bangkit.submissionmade1.core.data.source.remote

import android.util.Log
import com.bangkit.submissionmade1.core.data.source.remote.network.ApiResponse
import com.bangkit.submissionmade1.core.data.source.remote.network.ApiService
import com.bangkit.submissionmade1.core.data.source.remote.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllNews() : Flow<ApiResponse<List<ArticlesItem>>> {

        return flow {
            try {
                val response = apiService.getNews()
                val dataArr = response.articles

                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getHealthNews() : Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = apiService.getHealthNews()
                val dataArr = response.articles

                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSportsNews() : Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = apiService.getSportsNews()
                val dataArr = response.articles

                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}