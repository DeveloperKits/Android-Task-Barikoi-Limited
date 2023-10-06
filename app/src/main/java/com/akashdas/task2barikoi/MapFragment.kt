package com.akashdas.task2barikoi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap


class MapFragment : Fragment() {

    private lateinit var map: MapboxMap
    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireActivity())
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById(R.id.mapView)

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
            map.setStyle(styleUrl) { style->
                map.addMarker(
                    MarkerOptions()
                        .setPosition(LatLng(23.8345,90.38044))
                        .setTitle("Map Marker")

                )
            }
            map.cameraPosition = CameraPosition
                .Builder()
                .target(LatLng(23.8345,90.38044))
                .zoom(6.0)
                .build()
        }
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