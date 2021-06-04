package com.bangkit.submissionmade1.core.domain.usecase

import com.bangkit.submissionmade1.core.domain.model.News
import com.bangkit.submissionmade1.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow


class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {
    override fun getAllNews() = newsRepository.getAllNews()
    override fun getAllHealthNews() = newsRepository.getAllHealthNews()

    override fun getAllSportsNews() = newsRepository.getAllSportsNews()

    override fun getFavNews(): Flow<List<News>> = newsRepository.getFavNews()

    override fun setFavNews(news: News, state: Boolean) = newsRepository.setFavNews(news, state)

}