package com.emenjivar.transitions.di

import com.emenjivar.transitions.data.repository.AlbumRepository
import com.emenjivar.transitions.data.repository.AlbumRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideAlbumRepository(): AlbumRepository = AlbumRepositoryImp()
}
