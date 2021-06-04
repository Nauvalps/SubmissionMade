package com.bangkit.submissionmade1.ui.sports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.submissionmade1.core.domain.usecase.NewsUseCase

class SportsViewModel(newsUseCase: NewsUseCase) : ViewModel() {

    val sportsNews = newsUseCase.getAllSportsNews().asLiveData()
}