//package com.kanulp.esrandroidtest.ui.details
//
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import androidx.navigation.fragment.findNavController
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.MapView
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.kanulp.esrandroidtest.R
//import dagger.hilt.android.AndroidEntryPoint
//
///**
// * A simple [Fragment] subclass as the second destination in the navigation.
// */
//@AndroidEntryPoint
//class DetailsFragment : Fragment(), OnMapReadyCallback {
//
//    var mapView: MapView? = null
//    private var mMap: GoogleMap? = null
//
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view =  inflater.inflate(R.layout.fragment_details, container, false)
//        var args = DetailsFragmentArgs.fromBundle(requireArguments())
//        var id = args.id
//        Log.d("DETAILS","id is $id")
////        mMap = (view.findViewById<MapView>(R.id.mapView))
////            .getMap();
//        loadMap()
//        return view
//    }
//
//    private fun loadMap() {
//
//        mMap!!.clear()
//        latLng = place.latLng
//        title = place.name
//        val snippet = """
//                Address: ${place.address}
//                Phone number: ${place.phoneNumber}
//                rating${place.rating}
//                """.trimIndent()
//        mMap!!.addMarker(MarkerOptions().position(latLng!!).title(title).snippet(snippet))
//        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
//        mMap!!.setInfoWindowAdapter(PlacesAdapter(applicationContext))
//    }
//
//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
//        mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
//        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
//        mMap!!.addMarker(MarkerOptions().position(latLng!!).title("Current Location"))
//        mMap!!.uiSettings.isZoomControlsEnabled = true
//        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
//        mMap!!.setOnMapClickListener { destination ->
//            mMap!!.clear()
//            val options = MarkerOptions()
//            options.position(destination)
//            options.title("Lat=" + destination.latitude + ", Long=" + destination.longitude)
//            val marker = mMap!!.addMarker(options)
//            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 16f))
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        when (requestCode) {
//            REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                fetchCurrentLocation()
//            }
//        }
//    }
//
//
//}