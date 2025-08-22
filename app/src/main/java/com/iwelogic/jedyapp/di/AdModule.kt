package com.iwelogic.jedyapp.di

import com.iwelogic.ads.AdMobProvider
import com.iwelogic.ads.AdProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AdModule {

    @Provides
    @Singleton
    fun provideAds(): AdProvider {
        return AdMobProvider()
    }
}
