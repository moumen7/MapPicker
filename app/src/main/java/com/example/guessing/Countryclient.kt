package com.example.guessing


import android.util.Log
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Countryclient {

    val Url:String = "https://nominatim.openstreetmap.org/"
    private var getgeo: getGeo? = null


    init {
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        Log.v(TAG, "mid of call");
        getgeo = retrofit.create(getGeo::class.java)

    }
    companion object {
        private val TAG = "Model"
        public var instance: Countryclient? = null
        fun getINSTANCE(): Countryclient? {
            Log.v(TAG, "start of call");
            if (instance == null) {

                instance =
                    Countryclient()
            }
            return instance
        }
    }

    fun getcoordinates(country:String): Call<OSM?>? {
        return getgeo?.getcoordinates("json",country,1)
    }

    


}