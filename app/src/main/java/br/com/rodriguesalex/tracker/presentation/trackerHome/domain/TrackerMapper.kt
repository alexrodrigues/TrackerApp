package br.com.rodriguesalex.tracker.presentation.trackerHome.domain

import android.location.Location
import br.com.rodriguesalex.tracker.presentation.trackerHome.data.model.TrackLocation

fun Location.toTrackLocation(): TrackLocation =
    TrackLocation(
        latitude = latitude,
        longitude = longitude
    )