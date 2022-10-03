package com.example.secondprojectbymvvm.view.foodtracking

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val home = LatLng(33.216743564251914, -96.73570575575988)
        val restaurant = LatLng(33.21993965158354, -96.63552033084333)
        val midpoint = LatLng(33.218381717748215, -96.68222313092001)
        mMap.addPolygon(
            PolygonOptions().add(restaurant,midpoint,home)
                .strokeWidth(3f)
                .fillColor(Color.BLUE)
                .geodesic(true)
                .strokeColor(Color.RED)
        )
        mMap.addMarker(
            MarkerOptions().position(home).title("Marker in Home")
                .icon(bitmapVector(R.drawable.ic_baseline_home_24))
        )
        mMap.addMarker(
            MarkerOptions().position(midpoint).title("Marker in Driver")
                .icon(bitmapVector(R.drawable.ic_baseline_directions_car_24)
                )
        )
        mMap.addMarker(
            MarkerOptions().position(restaurant).title("Marker in Restaurant")
                .icon(bitmapVector(R.drawable.ic_baseline_liquor_24)
                )
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midpoint, 12f))
    }
    private fun bitmapVector(vectorId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(this, vectorId)
        vectorDrawable?.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        val bitmap = Bitmap.createBitmap(
            vectorDrawable!!.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


}