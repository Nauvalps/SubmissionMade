package com.bangkit.submissionmade1.core.domain.repository

import com.bangkit.submissionmade1.core.data.source.Resource
import com.bangkit.submissionmade1.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getAllNews() : Flow<Resource<List<News>>>

    fun getAllHealthNews() : Flow<Resource<List<News>>>

    fun getAllSportsNews() : Flow<Resource<List<News>>>

    fun getFavNews() : Flow<List<News>>

    fun setFavNews(news: News, state:Boolean)

}