package com.bangkit.submissionmade1.core.data.source.local.room

import androidx.room.*
import com.bangkit.submissionmade1.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getNewsAll() : Flow<List<NewsEntity>>


    @Query("SELECT * FROM news where categoryContent = 'health'")
    fun getNewsHealth() : Flow<List<NewsEntity>>


    @Query("SELECT * FROM news where categoryContent = 'sports'")
    fun getNewsSports() : Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where isFav = 1")
    fun getNewsFav() : Flow<List<NewsEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    fun updateFavUser(newsEntity: NewsEntity)
}