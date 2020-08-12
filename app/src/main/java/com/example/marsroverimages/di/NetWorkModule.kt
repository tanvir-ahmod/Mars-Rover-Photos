package com.example.marsroverimages.di

import com.example.marsroverimages.data.source.remote.RoverImageService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetWorkModule {
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder =
        Retrofit.Builder().baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Provides
    fun providesRoverService(retrofit: Retrofit.Builder) =
        retrofit.build().create(RoverImageService::class.java)

}