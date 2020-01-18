package br.com.rodriguesalex.tracker.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    private val TIMEOUT = 30L
    private val BASEURL = ""

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT,TimeUnit.SECONDS)
            .build()


    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

}
