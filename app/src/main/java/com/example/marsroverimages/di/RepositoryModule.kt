package com.example.marsroverimages.di

import com.example.marsroverimages.data.Source.RoverDataSource
import com.example.marsroverimages.data.Source.RoverRepository
import com.example.marsroverimages.data.Source.RoverRepositoryImpl
import com.example.marsroverimages.data.Source.local.LocalRoverDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun providesRoverRepository( roverRepository: RoverRepositoryImpl): RoverRepository

    @Binds
    abstract fun providesRoverLocalDataSource(roverDataSource: LocalRoverDataSource) : RoverDataSource
}