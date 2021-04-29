package com.example.guessing

data class OSMItem(
    val boundingbox: List<String>,
    val `class`: String,
    val display_name: String,
    val geojson: Geojson,
    val icon: String,
    val importance: Double,
    val lat: String,
    val licence: String,
    val lon: String,
    val osm_id: Int,
    val osm_type: String,
    val place_id: Int,
    val type: String

)