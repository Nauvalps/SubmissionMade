package com.bangkit.submissionmade1.ui.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.submissionmade1.core.domain.usecase.NewsUseCase

class HealthViewModel(newsUseCase: NewsUseCase) : ViewModel() {

    val healthNews = newsUseCase.getAllHealthNews().asLiveData()

}