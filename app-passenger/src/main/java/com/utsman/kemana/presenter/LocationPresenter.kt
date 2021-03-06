package com.utsman.kemana.presenter

import android.content.Context
import com.mapbox.mapboxsdk.geometry.LatLng
import com.utsman.kemana.impl.ILocationView
import com.utsman.kemana.impl.LocationInterface
import com.utsman.smartmarker.location.LocationWatcher
import com.utsman.smartmarker.mapbox.toLatLngMapbox

class LocationPresenter(private val context: Context) : LocationInterface {

    private lateinit var locationWatcher: LocationWatcher
    private var nowLatLng = LatLng()

    override fun initLocation(iLocationView: ILocationView) {
        locationWatcher = LocationWatcher(context)

        locationWatcher.getLocation { location ->
            iLocationView.onLocationReady(location.toLatLngMapbox())
        }
    }

    override fun getNowLocation(): LatLng {
        locationWatcher.getLocation {
            nowLatLng = it.toLatLngMapbox()
        }

        return nowLatLng
    }

    override fun onDestroy() {
        locationWatcher.stopLocationWatcher()
    }
}