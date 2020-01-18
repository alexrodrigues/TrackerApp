package br.com.rodriguesalex.tracker.presentation.trackerHome.domain

import br.com.rodriguesalex.tracker.presentation.trackerHome.data.TrackLocation
import io.reactivex.Single
import javax.inject.Inject

interface TrackerInteractor {
    fun sendLocations(list: List<TrackLocation>): Single<String>
}

class TrackerInteractorImpl @Inject constructor() : TrackerInteractor {
    override fun sendLocations(list: List<TrackLocation>): Single<String> =
        Single.just(list.toString())
}