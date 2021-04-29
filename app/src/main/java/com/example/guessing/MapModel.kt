package com.example.guessing

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapModel: ViewModel() {
    private val TAG = "Model"
    val geomutable: MutableLiveData<OSM?>? = MutableLiveData<OSM?>()

    fun getMovies(country:String) {
        Countryclient.getINSTANCE()?.getcoordinates(country)?.enqueue(object : Callback<OSM?> {
            override fun onResponse(
                call: Call<OSM?>,
                response: Response<OSM?>
            )
            {
                geomutable?.postValue(response.body())
            }

            override fun onFailure(
                call: Call<OSM?>,
                t: Throwable
            ) {
                Log.v(TAG, t.message.toString());
            }
        })
    }
}