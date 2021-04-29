package com.example.guessing


import android.content.res.Resources
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.maps.android.data.geojson.GeoJsonLayer
import org.json.JSONObject
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
     var layer:GeoJsonLayer? = null
    private lateinit var mMap: GoogleMap
    private var tv:TextView? = null

    private var mapmodel: MapModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        tv = findViewById(R.id.textView)
        mapmodel = ViewModelProviders.of(this).get(MapModel::class.java)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        googleMap
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.mapstyle

                )
            )
            if (!success) {

            }
        } catch (e: Resources.NotFoundException) {

        }
        // JSONObject containing the GeoJSON data

        mapmodel?.geomutable?.observe(this,
            Observer<OSM?> { it ->
                if (it != null && it.size!=0)
                {

                    layer?.removeLayerFromMap()

                    layer = GeoJsonLayer(googleMap, JSONObject(Gson().toJson(it.get(0).geojson)))
                    layer?.defaultPolygonStyle?.fillColor = Color.rgb(145,45,60)
                    layer?.defaultPolygonStyle?.strokeColor = Color.BLACK
                    layer?.defaultPolygonStyle?.strokeWidth = 1F
                    layer?.addLayerToMap()

                }


            })
        // Add a marker in Sydney and move the camera

        googleMap.setOnMapClickListener {

            //Get the lat long here from variable position
            //Use GeoCoder class to fetch the country from latlong like this
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses: List<Address> = geocoder.getFromLocation(it.latitude, it.longitude, 1)
            if (addresses.size > 0) {
                val country: String? = addresses[0].getCountryName()

                if (country != null) {
                    mapmodel!!.getMovies(country)
                }
                Toast.makeText(this,country,Toast.LENGTH_LONG).show()
            }
        }
    }


}