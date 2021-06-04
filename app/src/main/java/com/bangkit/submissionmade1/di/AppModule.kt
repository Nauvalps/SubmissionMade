package com.bangkit.submissionmade1.di

import com.bangkit.submissionmade1.core.domain.usecase.NewsInteractor
import com.bangkit.submissionmade1.core.domain.usecase.NewsUseCase
import com.bangkit.submissionmade1.ui.detail.DetailNewsViewModel
import com.bangkit.submissionmade1.ui.health.HealthViewModel
import com.bangkit.submissionmade1.ui.headlines.HeadlinesViewModel
import com.bangkit.submissionmade1.ui.sports.SportsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HeadlinesViewModel(get()) }
    viewModel { DetailNewsViewModel(get()) }
    viewModel { HealthViewModel(get()) }
    viewModel { SportsViewModel(get()) }
}