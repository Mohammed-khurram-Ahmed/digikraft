package com.mohammed.khurram.digikraft.uiviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mohammed.khurram.digikraft.R
import com.mohammed.khurram.digikraft.databinding.FragmentDetailsBinding
import com.mohammed.khurram.digikraft.models.Feature
import com.mohammed.khurram.digikraft.utils.AppConstants
import com.mohammed.khurram.digikraft.utils.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    /*
     * binding object for xml views and kotlin code
     */
    lateinit var binding: FragmentDetailsBinding

    /**
     * Android fragment lifecycle  callbacks
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    /**
     * Android fragment lifecycle  callbacks
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadGoogleMap()
    }

    /**
     * Load google map on the screen
     */
    private fun loadGoogleMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(onMapReadyCallback)
    }

    /**
     * callback of google map ready, We can perform other map operation like add marker etc on the map
     */
    private val onMapReadyCallback = OnMapReadyCallback { googleMap ->
        /*
        * get data from previous screen from parcelable
         */
        val feature = arguments?.getParcelable<Feature>(AppConstants.DATA)

        /*
         * checks whether coordinate available or not and show error
         */
        if (feature?.geometry?.coordinates != null && feature.geometry.coordinates.size == 2) {

            val sydney = LatLng(feature.geometry.coordinates[0], feature.geometry.coordinates[1])
            googleMap.addMarker(
                MarkerOptions().position(sydney)
                    .title("${feature.properties.label} \n ${feature.properties.bikes}")
                    .icon(BitmapDescriptorFactory.fromBitmap(Utility.createSmallMarker(context)))
            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 9.5f))
        } else {
            Toast.makeText(context, getString(R.string.cordinate_error), Toast.LENGTH_LONG).show()
        }
        /*
         * checks whether data available or not and show error.
         */
        if (feature?.properties != null) {
            binding.includedView.item = feature.properties
        } else {
            Toast.makeText(context, getString(R.string.data_error), Toast.LENGTH_LONG).show()
        }

    }


}