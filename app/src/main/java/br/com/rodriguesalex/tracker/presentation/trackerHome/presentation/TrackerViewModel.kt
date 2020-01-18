package br.com.rodriguesalex.tracker.presentation.trackerHome.presentation

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rodriguesalex.tracker.di.qualifiers.RxIoThread
import br.com.rodriguesalex.tracker.di.qualifiers.RxMainThread
import br.com.rodriguesalex.tracker.presentation.trackerHome.domain.TrackerInteractor
import br.com.rodriguesalex.tracker.presentation.trackerHome.domain.toTrackLocation
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList

sealed class TrackerViewModelState {
    object LocationDenied: TrackerViewModelState()
    object LocationFailed: TrackerViewModelState()
}
class TrackerViewModel @Inject constructor(
    private val interactor: TrackerInteractor,
    @RxMainThread val mainThread: Scheduler,
    @RxIoThread   val ioThread: Scheduler
): ViewModel() {

    private val disposable = CompositeDisposable()

    val latitudeText = MutableLiveData<String>()
    val longitudeText = MutableLiveData<String>()
    private var list: ArrayList<Location> = ArrayList()

    fun update(location: Location) {
        latitudeText.value = "Latitude: ${location.latitude}"
        longitudeText.value = "Longitude: ${location.longitude}"
        list.add(location)
        if (list.size == 1) {
            startTrackerPool()
        }
    }

    private fun startTrackerPool() {
        disposable.add(Observable
            .interval(25, TimeUnit.SECONDS)
            .repeat()
            .observeOn(mainThread)
            .subscribe {
                sendLocations()
            })
    }

    private fun sendLocations() {
        disposable.add(interactor.sendLocations(list.map { it.toTrackLocation() })
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .subscribe({
                println(it)
            }, {
                it.printStackTrace()
            }))
    }
}