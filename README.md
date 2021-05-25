# Click any country from map and the country gets highlighted.


## Simple overview
* Project is built using android/kotlin and following MVVM architecture pattern.
* Open street maps is used to obtain the coordinates of the selected country, and the response is handled by Retrofit. [OSM Api documentation](https://nominatim.org/release-docs/latest/api/Search/)
* Google maps Geojson utility takes the coordinates and draws over the map. [Google maps Geojson utility](https://developers.google.com/maps/documentation/android-sdk/utility/geojson)
* [Map-styling](https://developers.google.com/maps/documentation/android-sdk/styling)



## Short Demo
<img src="https://user-images.githubusercontent.com/57041674/116604523-88952e80-a92e-11eb-9b18-cb42b11c6dec.gif" width="250" height="550"/>

## Important notes
* Some countries might not repond due to OSM not providing response.
* Project can be slow due to response taking a long time.(Can be handled either by downloading the coordinates(Will Implement this solution soon), Caching responses or using a Geochart with webview (Documentation of Geochart)[https://developers.google.com/chart/interactive/docs/gallery/geochart]
* (Faster and offline version)[https://github.com/moumen7/Map_picker]
