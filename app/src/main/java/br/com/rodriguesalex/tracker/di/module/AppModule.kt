package br.com.rodriguesalex.tracker.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import br.com.rodriguesalex.tracker.di.qualifiers.RxIoThread
import br.com.rodriguesalex.tracker.di.qualifiers.RxMainThread
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton
import android.content.Context.LOCATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.location.LocationManager
import br.com.rodriguesalex.tracker.di.scope.ApplicationScope
import br.com.rodriguesalex.tracker.presentation.trackerHome.presentation.TrackerActivity
import br.com.rodriguesalex.tracker.presentation.trackerHome.presentation.TrackerViewModel
import com.robin.locationgetter.EasyLocation
import dagger.Binds


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
}