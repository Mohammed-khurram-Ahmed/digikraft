package com.mohammed.khurram.digikraft.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mohammed.khurram.digikraft.models.Feature
import com.mohammed.khurram.digikraft.models.NetworkResult
import com.mohammed.khurram.digikraft.remote.RemoteAPI
import com.mohammed.khurram.digikraft.utils.NetworkUtils
import javax.inject.Inject

/**
 * Repository class to manage data from different data sources, like remote data source or local datasource
 */
class Repository @Inject constructor(private val remoteInterface: RemoteAPI, private val  application: Application) {

    var mutableLiveData = MutableLiveData<NetworkResult<List<Feature>>>()

    suspend fun getCitiesData() {
        mutableLiveData.postValue(NetworkResult.Loading())

        if (NetworkUtils.isInternetAvailable(application)) {
            val bikeStation = remoteInterface.getStations()
            if (bikeStation?.body() != null) {
                val stations = bikeStation.body()!!.features
                mutableLiveData.postValue(NetworkResult.Success(stations))
            } else {
                mutableLiveData.postValue(NetworkResult.Error(502, "No Data"))
            }
        }else{
            mutableLiveData.postValue(NetworkResult.Error(502, "No Network"))

        }


    }

}