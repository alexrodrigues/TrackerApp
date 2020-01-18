package br.com.rodriguesalex.tracker.presentation.trackerHome.di

import androidx.lifecycle.ViewModel
import br.com.rodriguesalex.tracker.di.module.ViewModelKey
import br.com.rodriguesalex.tracker.presentation.trackerHome.domain.TrackerInteractor
import br.com.rodriguesalex.tracker.presentation.trackerHome.domain.TrackerInteractorImpl
import br.com.rodriguesalex.tracker.presentation.trackerHome.presentation.TrackerActivity
import br.com.rodriguesalex.tracker.presentation.trackerHome.presentation.TrackerViewModel
import com.example.marvelapp.di.scope.ActivityScope
import com.robin.locationgetter.EasyLocation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class TrackerModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(TrackerViewModel::class)
    abstract fun bindTrackerViewModel(trackViewModel: TrackerViewModel): ViewModel

    @ActivityScope
    @Binds
    abstract fun provideInteractor(interactor: TrackerInteractorImpl): TrackerInteractor

}