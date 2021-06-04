package com.bangkit.submissionmade1.ui.detail

import androidx.lifecycle.ViewModel
import com.bangkit.submissionmade1.core.domain.model.News
import com.bangkit.submissionmade1.core.domain.usecase.NewsUseCase

class DetailNewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {

    fun setFavNews(news: News, newStatus: Boolean) = newsUseCase.setFavNews(news, newStatus)
}