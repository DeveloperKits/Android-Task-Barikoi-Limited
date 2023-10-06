package com.akashdas.task2barikoi.Fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.akashdas.task2barikoi.R
import com.akashdas.task2barikoi.databinding.FragmentHomeBinding
import com.akashdas.task2barikoi.utils.areLocationPermissionsGranted

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val LOCATION_PERMISSION_REQUEST_CODE = 123
    private var permissionDenied = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.yourLocation.setOnClickListener {
            if (areLocationPermissionsGranted(requireContext())){
                findNavController().navigate(R.id.action_home_to_map)
            }else {
                requestLocationPermissions()
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun requestLocationPermissions() {
        val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        val coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                fineLocationPermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(fineLocationPermission)
        }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                coarseLocationPermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(coarseLocationPermission)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {

            } else {
                permissionDenied = true
            }
        }
    }

}