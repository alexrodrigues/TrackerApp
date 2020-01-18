package br.com.rodriguesalex.tracker.di.component

import android.app.Application
import br.com.rodriguesalex.tracker.application.AppTracker
import br.com.rodriguesalex.tracker.di.module.ActivityBuilderModule
import br.com.rodriguesalex.tracker.di.module.AppModule
import br.com.rodriguesalex.tracker.di.module.NetworkModule
import br.com.rodriguesalex.tracker.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules =[
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    ViewModelModule::class,
    NetworkModule::class])
interface AppComponent : AndroidInjector<AppTracker> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}