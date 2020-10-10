package br.com.rodriguesalex.tracker.presentation.trackerHome.domain

import br.com.rodriguesalex.tracker.presentation.trackerHome.data.model.TrackLocation
import br.com.rodriguesalex.tracker.presentation.trackerHome.data.service.TrackerService
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.Single
import java.lang.RuntimeException
import javax.inject.Inject

interface TrackerInteractor {
    fun sendLocations(list: List<TrackLocation>): Single<Pair<Boolean, String>>
}

class TrackerInteractorImpl @Inject constructor(
    val service: TrackerService,
    val crashlytics: FirebaseCrashlytics
) : TrackerInteractor {

    override fun sendLocations(list: List<TrackLocation>) =
        service.sendLocation(list).map {
            //TODO log here
            crashlytics.recordException(RuntimeException(
                "$it"
            ))
            Pair(it.isSuccessful, it.message())
        }

}
