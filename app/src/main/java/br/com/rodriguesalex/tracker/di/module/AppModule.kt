package br.com.rodriguesalex.tracker.di.module

import android.app.Application
import android.content.res.Resources
import br.com.rodriguesalex.tracker.di.qualifiers.RxIoThread
import br.com.rodriguesalex.tracker.di.qualifiers.RxMainThread
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideResources(application : Application): Resources = application.resources

    @Provides
    @Singleton
    @RxIoThread
    fun provideIOScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @RxMainThread
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun provideCrashlytics(): FirebaseCrashlytics = FirebaseCrashlytics.getInstance()
}