package br.com.rodriguesalex.tracker.presentation.trackerHome.data.service

import br.com.rodriguesalex.tracker.presentation.trackerHome.data.model.RodriguesResponse
import br.com.rodriguesalex.tracker.presentation.trackerHome.data.model.TrackLocation
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TrackerService {
    @POST("Route")
    fun sendLocation(@Body locations: List<TrackLocation>): Single<Response<RodriguesResponse>>
}