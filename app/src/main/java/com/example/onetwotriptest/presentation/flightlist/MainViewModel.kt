package com.example.onetwotriptest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onetwotriptest.data.network.model.Flight
import com.example.onetwotriptest.domain.FlightRepository
import com.example.onetwotriptest.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

enum class FlightApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _flightList = MutableLiveData<Response<List<Flight>>>()
    val flightList: LiveData<Response<List<Flight>>> = _flightList

    private val _status = MutableLiveData<FlightApiStatus>()
    val status: LiveData<FlightApiStatus> = _status

    fun getFlightList() {
        viewModelScope.launch {
            _status.value = FlightApiStatus.LOADING
            try {
                val response = repository.getCountries()
                _flightList.value = response
                _status.value = FlightApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FlightApiStatus.ERROR
            }
        }
    }
}