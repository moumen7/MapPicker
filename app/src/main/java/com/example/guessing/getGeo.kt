package com.example.guessing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface getGeo {
    @GET(  "search")
    fun getcoordinates( @Query("format") format:
    String, @Query("country") country: String, @Query("polygon_geojson") polygon_geojson:Int) : Call<OSM?>?

}