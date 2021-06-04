package com.bangkit.submissionmade1.core.data.source

import com.bangkit.submissionmade1.core.data.source.local.LocalDataSource
import com.bangkit.submissionmade1.core.data.source.remote.RemoteDataSource
import com.bangkit.submissionmade1.core.data.source.remote.network.ApiResponse
import com.bangkit.submissionmade1.core.data.source.remote.response.ArticlesItem
import com.bangkit.submissionmade1.core.domain.model.News
import com.bangkit.submissionmade1.core.domain.repository.INewsRepository
import com.bangkit.submissionmade1.core.utils.AppExecutors
import com.bangkit.submissionmade1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDb(): Flow<List<News>> {
                return localDataSource.getNewsAll().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getAllNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponseToEntitiesHeadline(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()


    override fun getAllHealthNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDb(): Flow<List<News>> {
                return localDataSource.getNewsHealth().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getHealthNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponseToEntitiesHealth(data)
                localDataSource.insertNews(newsList)
            }

        }.asFlow()

    override fun getAllSportsNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDb(): Flow<List<News>> {
                return localDataSource.getNewsSports().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getSportsNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponseToEntitiesSports(data)
                localDataSource.insertNews(newsList)
            }

        }.asFlow()

    override fun getFavNews(): Flow<List<News>> {
        return localDataSource.getNewsFav().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }


    override fun setFavNews(news: News, state: Boolean) {
        val setNewsFav = DataMapper.mapDomainToEntities(news)
        appExecutors.diskIO().execute { localDataSource.setIsFavUser(setNewsFav, state) }
    }
}