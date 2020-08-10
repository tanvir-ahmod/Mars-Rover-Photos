package com.example.marsroverimages.di

import com.example.marsroverimages.data.Source.RoverDataSource
import com.example.marsroverimages.data.Source.local.LocalRoverDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    fun providesRoverLocalDataSource() : RoverDataSource = LocalRoverDataSource()

}