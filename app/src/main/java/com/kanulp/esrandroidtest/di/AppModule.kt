package com.kanulp.esrandroidtest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kanulp.esrandroidtest.data.remote.NearByApiImpl
import com.kanulp.esrandroidtest.data.remote.NearByApiService
import com.kanulp.esrandroidtest.repository.NearByRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
            .baseUrl("https://engine.kissakired.com/api/v5/feed/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    fun provideNearByApiService(retrofit: Retrofit): NearByApiService = retrofit.create(NearByApiService::class.java)

    @Singleton
    @Provides
    fun provideNearByImpl(apiservice: NearByApiService) = NearByApiImpl(apiservice)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: NearByApiImpl) =
            NearByRepository(remoteDataSource)
}