package com.android.nynews.di

import com.android.nynews.repos.NewsRepository
import com.android.nynews.ui.news.NewsListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Module that provides instances of ViewModels
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    @ActivityRetainedScoped
    fun provideNewsListViewModel(articlesRepository: NewsRepository) : NewsListViewModel {
        return NewsListViewModel(articlesRepository)
    }

}
