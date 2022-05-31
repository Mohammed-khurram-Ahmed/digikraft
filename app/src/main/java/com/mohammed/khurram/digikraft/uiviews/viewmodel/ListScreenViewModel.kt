package com.mohammed.khurram.digikraft.uiviews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammed.khurram.digikraft.models.Feature
import com.mohammed.khurram.digikraft.models.NetworkResult
import com.mohammed.khurram.digikraft.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    /*
     * live data to observe data from remote server
     */
    val immutableLiveData: LiveData<NetworkResult<List<Feature>>>
        get() = repository.mutableLiveData

    init {
        getData()
    }

    /**
     * get data in coroutine background IO thread
     */
    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCitiesData()
        }
    }

}