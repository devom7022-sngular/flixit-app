package com.sngular.flixitApp.ui.view

import android.Manifest.*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.ActivityMainBinding
import com.sngular.flixitApp.ui.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val locationViewModel: LocationViewModel by viewModels()

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private val interval: Long = 10000 // 10seconds
    private val fastestInterval: Long = 60000 * 5
    private lateinit var mLastLocation: Location
    private lateinit var mLocationRequest: LocationRequest
    private val requestPermissionCode = 888
    private val requestPermissionCode2 = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.frContainer)

        binding.btmNavView.setupWithNavController(navController)

        locationViewModel.onCreate()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        mLocationRequest = LocationRequest.create()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showAlertMessage()
        }

        checkForPermission(this)
        checkForPermissionNotification(this)
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationProviderClient?.removeLocationUpdates(mLocationCallback)
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation
            //locationChanged(locationResult.lastLocation!!)
            latitude = locationResult.lastLocation!!.latitude
            longitude = locationResult.lastLocation!!.longitude

            locationViewModel.setLocation(
                latitude.toString(), longitude.toString(),
                getCurrentTime()!!
            )

            notificate(
                "Ubicación guardada en storage",
                "¡Su ubicación se ha almacenado ya puede visualizarla!",
                "LOCATION_ID",
                "Ubicación",
                R.drawable.ic_location
            )
        }
    }

    private fun getCurrentTime(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())
        } else {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            sdf.format(Date())
        }
    }

    private fun startLocationUpdates() {
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = interval
        mLocationRequest.fastestInterval = fastestInterval

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this, permission.ACCESS_FINE_LOCATION
            ) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, permission.ACCESS_COARSE_LOCATION
            ) != PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()!!
        )
    }

    private fun checkForPermission(context: Context): Boolean {
        return if (context.checkSelfPermission(permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(permission.ACCESS_FINE_LOCATION),
                requestPermissionCode
            )
            false
        }
    }

    private fun showAlertMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("El permiso de ubicación esta deshabilitado, desea habilitarlo?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 10
                )
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    /*fun locationChanged(location: Location) {
        mLastLocation = location
        longitude = mLastLocation.longitude
        latitude = mLastLocation.latitude
        locationViewModel.setLocation(latitude.toString(), longitude.toString(), getCurrentTime()!!)
        notificate(
            "Ubicación guardada en storage",
            "¡Su ubicación se ha almacenado ya puede visualizarla!",
            "LOCATION_ID",
            "Ubicación",
            R.drawable.ic_location
        )
    }*/

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestPermissionCode) {
            if (grantResults[0] == PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun notificate(
        title: String,
        text: String,
        channelId: String,
        channelName: String,
        icImage: Int
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.MAGENTA
                enableLights(true)
            }

            val manager =
                baseContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(baseContext, channelId)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(icImage)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(baseContext)) {

            if (ActivityCompat.checkSelfPermission(
                    baseContext,
                    permission.POST_NOTIFICATIONS
                ) != PERMISSION_GRANTED
            ) {
                return
            }

            val random = kotlin.math.abs((0..999999999999).random())

            notify(random.toInt(), builder.build())
        }
    }

    private fun checkForPermissionNotification(context: Context): Boolean {
        return if (context.checkSelfPermission(permission.POST_NOTIFICATIONS) == PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(permission.POST_NOTIFICATIONS),
                requestPermissionCode2
            )
            false
        }
    }
}