package com.bangkit.submissionmade1.ui.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.submissionmade1.core.domain.usecase.NewsUseCase

class HeadlinesViewModel(newsUseCase: NewsUseCase) : ViewModel() {

    val news = newsUseCase.getAllNews().asLiveData()
}