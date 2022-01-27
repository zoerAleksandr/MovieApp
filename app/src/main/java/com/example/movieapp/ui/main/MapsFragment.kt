package com.example.movieapp.ui.main

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment() {

    private lateinit var map: GoogleMap

    companion object {
        fun newInstance(bundle: Bundle?) = MapsFragment().also {
            it.arguments = bundle
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map = googleMap
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        arguments?.getString(ActorFragment.LOCATION)?.let {
            showPlaceOfBirth(it)
            Log.d("debug", "onViewCreated $it")
        }
    }

    private fun showPlaceOfBirth(placeOfBirth: String) {
        val geoCoder = Geocoder(requireContext())
        Thread {
            try {
                val address = geoCoder.getFromLocationName(placeOfBirth, 1)
                if (address.size > 0) {
                    goToAddress(address, placeOfBirth)
                }
            } catch (e: IOException) {
                Log.d("debug", "showPlaceOfBirth $e")
            }
        }.start()
    }

    private fun goToAddress(
        addresses: MutableList<Address>,
        placeOfBirth: String
    ) {
        val location = LatLng(addresses[0].latitude, addresses[0].longitude)
        view?.post {
            setMarker(location, placeOfBirth, R.drawable.flag)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 5f))
        }
    }

    private fun setMarker(location: LatLng, placeOfBirth: String, resourceId: Int): Marker? {
        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(placeOfBirth).icon(BitmapDescriptorFactory.fromResource(resourceId))
        )
    }
}