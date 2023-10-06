package com.akashdas.task2barikoi.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


fun areLocationPermissionsGranted(context: Context): Boolean {
    val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    val coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION
    return (ContextCompat.checkSelfPermission(context, fineLocationPermission)
            == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, coarseLocationPermission)
            == PackageManager.PERMISSION_GRANTED)
}

