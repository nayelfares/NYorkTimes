package com.android.nynews.di

import android.app.Application
import androidx.room.Room
import com.android.nynews.BuildConfig.DATABASE_NAME
import com.android.nynews.room.AppDatabase
import com.android.nynews.room.ArticlesDao
import com.android.nynews.room.TypeResponseConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that provides Database related functions
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application, typeResponseConverter: TypeResponseConverter): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .addTypeConverter(typeResponseConverter)
      .build()
  }

  @Provides
  @Singleton
  fun provideNewsDao(appDatabase: AppDatabase): ArticlesDao {
    return appDatabase.articlesDao()
  }

  @Provides
  @Singleton
  fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
    return TypeResponseConverter(moshi)
  }

}
