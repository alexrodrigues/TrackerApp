package br.com.rodriguesalex.tracker.di.module

import br.com.rodriguesalex.tracker.presentation.trackerHome.di.TrackerModule
import br.com.rodriguesalex.tracker.presentation.trackerHome.presentation.TrackerActivity
import com.example.marvelapp.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [TrackerModule::class])
    internal abstract fun trackerActivity(): TrackerActivity

}
