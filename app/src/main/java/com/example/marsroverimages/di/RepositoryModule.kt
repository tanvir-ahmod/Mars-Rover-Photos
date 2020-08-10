package com.example.marsroverimages.di

import com.example.marsroverimages.data.Source.RoverDataSource
import com.example.marsroverimages.data.Source.RoverRepository
import com.example.marsroverimages.data.Source.RoverRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun providesRoverRepository(roverDataSource: RoverDataSource): RoverRepository =
        RoverRepositoryImpl(roverDataSource)
}