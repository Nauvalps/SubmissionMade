package com.bangkit.di

import com.bangkit.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favNewsModule = module {
    viewModel { FavoriteViewModel(get()) }
}