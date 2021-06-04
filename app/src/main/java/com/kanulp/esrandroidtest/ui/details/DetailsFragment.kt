package com.kanulp.esrandroidtest.ui.details

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.PolyUtil
import com.kanulp.esrandroidtest.R
import com.kanulp.esrandroidtest.data.model.Item
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    var mapView: MapView? = null
    private var mMap: GoogleMap? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_details, container, false)
        var args = DetailsFragmentArgs.fromBundle(requireArguments())

        mapView = view.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.onResume()
        try {
            MapsInitializer.initialize(this.activity);
            //getting id
            val id = args.id
            val dataType : Type = object :
                    TypeToken<Item?>() {}.type
            val gson = Gson()
            val data : Item =
                    gson.fromJson(id, dataType)

            if(data.ctype=="POI"){

                var location = LatLng(data.location.lat, data.location.lng)
                Log.d("DETAILS", "IN POI $location")
                setUpMapIfNeeded(location)
            }
            else
            {
            var original = LatLng(data.data.origin.lat, data.data.origin.lng)
            var destination = LatLng(data.data.destination.lat, data.data.destination.lng)
            var polyLine = data.data.polyline.toString()

            var decoded = PolyUtil.decode(polyLine)
            val options = PolylineOptions().width(10f).color(Color.BLUE)
            for (i in decoded){
                options.add(i)
            }
            setUpMapIfNeeded(original,destination,options)
        }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return view
    }

    private fun setUpMapIfNeeded(location: LatLng,locationDestination: LatLng? = null,polylineOptions: PolylineOptions?=null) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mapView?.getMapAsync {
                mMap = it
                if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions(requireActivity(),
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
                } else {
                        ActivityCompat.requestPermissions(requireActivity(),
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
                    }

                mMap?.addMarker(MarkerOptions().position(location).title("START"))
                if(locationDestination!=null)
                    mMap?.addMarker(MarkerOptions().position(locationDestination!!).title("Destination"))
                val cameraPosition = CameraPosition.Builder().target(location).zoom(12f).build()
                mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                if(polylineOptions!=null)
                    mMap?.addPolyline(polylineOptions);
            }
        }
    }
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(requireActivity(),
                                    Manifest.permission.ACCESS_FINE_LOCATION) ===
                                    PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(requireActivity(), "Permission Granted", Toast.LENGTH_SHORT).show()
                        mMap?.isMyLocationEnabled = true

                    }
                } else {
                    Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

}