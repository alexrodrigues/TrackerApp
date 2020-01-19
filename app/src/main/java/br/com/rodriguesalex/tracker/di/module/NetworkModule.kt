package br.com.rodriguesalex.tracker.di.module

import br.com.rodriguesalex.tracker.presentation.trackerHome.data.service.TrackerService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    private val TIMEOUT = 30L
    private val BASEURL = "https://app-alexrodrigues-tracker.herokuapp.com/"


    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideTrackerApi(retrofit: Retrofit): TrackerService {
        return retrofit.create(TrackerService::class.java)
    }

}
