package com.akashdas.task2barikoi.Fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.akashdas.task2barikoi.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap

class NearPlaceFragment : Fragment() {

    private lateinit var map: MapboxMap
    private lateinit var mapView: MapView

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var latitude = 0.0
    private var longitude = 0.0
    private val styleId = "osm-liberty"
    private val apiKey = getString(R.string.BariKoiApiKey)
    private val styleUrl = "https://map.barikoi.com/styles/$styleId/style.json?key=$apiKey"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireActivity())
        val view = inflater.inflate(R.layout.fragment_near_place, container, false)

        mapView = view.findViewById(R.id.mapView)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getCurrentLocation()

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { map ->
            this.map = map
            map.setStyle(styleUrl)
        }
    }

    private fun getCurrentLocation(){

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude

                        map.cameraPosition = CameraPosition
                            .Builder()
                            .target(LatLng(latitude, longitude))
                            .zoom(10.0)
                            .build()
                    }
                }
                .addOnFailureListener { e ->
                    // Handle any errors that occurred while getting the location
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}