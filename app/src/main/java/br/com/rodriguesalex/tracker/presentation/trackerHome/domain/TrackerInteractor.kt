package br.com.rodriguesalex.tracker.presentation.trackerHome.domain

import br.com.rodriguesalex.tracker.presentation.trackerHome.data.model.TrackLocation
import br.com.rodriguesalex.tracker.presentation.trackerHome.data.service.TrackerService
import io.reactivex.Single
import javax.inject.Inject

interface TrackerInteractor {
    fun sendLocations(list: List<TrackLocation>): Single<Pair<Boolean, String>>
}

class TrackerInteractorImpl @Inject constructor(
    val service: TrackerService) : TrackerInteractor {

    override fun sendLocations(list: List<TrackLocation>) =
        service.sendLocation(list).map {
            Pair(it.isSuccessful, it.message())
        }.doOnError {
            it.printStackTrace()
        }

}
