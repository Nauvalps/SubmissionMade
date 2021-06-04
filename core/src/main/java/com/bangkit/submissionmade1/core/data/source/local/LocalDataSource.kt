package com.bangkit.submissionmade1.core.data.source.local

import com.bangkit.submissionmade1.core.data.source.local.entity.NewsEntity
import com.bangkit.submissionmade1.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsDao: NewsDao) {

    fun getNewsAll() : Flow<List<NewsEntity>> = newsDao.getNewsAll()

    fun getNewsHealth() : Flow<List<NewsEntity>> = newsDao.getNewsHealth()

    fun getNewsSports() : Flow<List<NewsEntity>> = newsDao.getNewsSports()

    fun getNewsFav() : Flow<List<NewsEntity>> = newsDao.getNewsFav()

    suspend fun insertNews(newsList: List<NewsEntity>)  = newsDao.insertNews(newsList)

    fun setIsFavUser(newsEntity: NewsEntity, newState: Boolean) {
        newsEntity.isFav = newState
        newsDao.updateFavUser(newsEntity)
    }
}