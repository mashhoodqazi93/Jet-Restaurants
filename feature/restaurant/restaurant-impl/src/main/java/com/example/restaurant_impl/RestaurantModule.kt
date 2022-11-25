package com.example.restaurant_impl

import com.example.restaurant_api.data.RestaurantRepository
import com.example.restaurant_impl.data.RestaurantRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RestaurantModule {

    @Singleton
    @Provides
    fun provideRepository(repository: RestaurantRepositoryImpl): RestaurantRepository = repository
}