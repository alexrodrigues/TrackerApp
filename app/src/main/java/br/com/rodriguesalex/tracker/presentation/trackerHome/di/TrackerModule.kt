package br.com.rodriguesalex.tracker.presentation.trackerHome.di

import androidx.lifecycle.ViewModel
import br.com.rodriguesalex.tracker.di.module.ViewModelKey
import br.com.rodriguesalex.tracker.presentation.trackerHome.presentation.TrackerViewModel
import com.example.marvelapp.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TrackerModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(TrackerViewModel::class)
    abstract fun bindTrackerViewModel(authViewModel: TrackerViewModel): ViewModel

}