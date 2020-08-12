package com.example.marsroverimages.di

import com.example.marsroverimages.data.source.RoverRepository
import com.example.marsroverimages.data.source.RoverRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun providesRoverRepository( roverRepository: RoverRepositoryImpl): RoverRepository
}