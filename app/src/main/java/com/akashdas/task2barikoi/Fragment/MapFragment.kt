package com.akashdas.task2barikoi.Fragment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.akashdas.task2barikoi.Dialog.BottomSheetDialog
import com.akashdas.task2barikoi.R
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.engine.LocationEngineRequest
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style


class MapFragment : Fragment() {

    private lateinit var map: MapboxMap
    private lateinit var mapView: MapView

    private lateinit var customInfoWindowView: View
    private lateinit var customInfoWindowTitle: TextView
    private lateinit var customInfoWindowSnippet: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireActivity())
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById(R.id.mapView)

        // Inflate the custom info window layout
        customInfoWindowView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_info_window, null)
        customInfoWindowTitle = customInfoWindowView.findViewById(R.id.titleTextView)
        customInfoWindowSnippet = customInfoWindowView.findViewById(R.id.snippetTextView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val styleId = "osm-liberty"
        val apiKey = getString(R.string.BariKoiApiKey)
        val styleUrl = "https://map.barikoi.com/styles/$styleId/style.json?key=$apiKey"

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {map ->
            this.map = map
            //map.setStyle("https://demotiles.maplibre.org/style.json")

            map.setStyle(styleUrl) {

                map.setOnMarkerClickListener { marker ->
                    // Pass data to the bottom sheet fragment
                    val args = Bundle()
                    args.putString("text1", marker.title.toString())
                    args.putString("text2", marker.position.toString())
                    val bottomSheetFragment = BottomSheetDialog()
                    bottomSheetFragment.arguments = args
                    bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
                    true
                }

                setMapCurrentLocationLayer()
            }


            map.cameraPosition = CameraPosition
                .Builder()
                .target(LatLng(23.8345,90.38044))
                .zoom(15.0)
                .build()
        }
    }

    private fun setMapCurrentLocationLayer() {
        map.let { it ->
            val locationComponent = it.locationComponent
            val locationComponentOptions =
                LocationComponentOptions.builder(requireContext())
                    .pulseEnabled(true)
                    .bearingTintColor(Color.BLACK)
                    .compassAnimationEnabled(true)
                    .build()

            it.style?.let {
                val locationComponentActivationOptions =
                    buildLocationComponentActivationOptions(it, locationComponentOptions)
                locationComponent.activateLocationComponent(locationComponentActivationOptions)
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                locationComponent.isLocationComponentEnabled = true

                if (locationComponent.isLocationComponentEnabled) {
                    // Get the last known location
                    val lastLocation = locationComponent.lastKnownLocation

                    // Check if the last known location is not null
                    if (lastLocation != null) {
                        val latitude = lastLocation.latitude
                        val longitude = lastLocation.longitude

                        //add marker in the map
                        map.addMarker(
                            MarkerOptions()
                                .setPosition(LatLng(latitude, longitude))
                                .title("Your Location")
                                .setTitle("Your Location")
                        )
                    }
                }

                locationComponent.cameraMode = CameraMode.TRACKING_GPS

            }
        }
    }

    private fun buildLocationComponentActivationOptions(
        style: Style,
        locationComponentOptions: LocationComponentOptions
    ): LocationComponentActivationOptions {
        return LocationComponentActivationOptions
            .builder(requireContext(), style)
            .locationComponentOptions(locationComponentOptions)
            .useDefaultLocationEngine(true)
            .locationEngineRequest(
                LocationEngineRequest.Builder(750)
                    .setFastestInterval(750)
                    .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                    .build()
            )
            .build()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }
}



