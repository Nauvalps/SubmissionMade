package com.bangkit.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.submissionmade1.core.domain.usecase.NewsUseCase

class FavoriteViewModel(newsUseCase: NewsUseCase) : ViewModel() {

    val favNews = newsUseCase.getFavNews().asLiveData()
}