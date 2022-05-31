package com.mohammed.khurram.digikraft.remote

import com.mohammed.khurram.digikraft.models.BikeStation
import retrofit2.Response
import retrofit2.http.GET

/**
 * remote interface for end point
 */
interface RemoteAPI {
    @GET("/mim/plan/map_service.html?mtype=pub_transport&co=stacje_rowerowe")
    suspend fun getStations(): Response<BikeStation>
}