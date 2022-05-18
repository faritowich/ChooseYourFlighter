package com.example.onetwotriptest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onetwotriptest.data.model.Flight
import com.example.onetwotriptest.domain.FlightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

enum class FlightApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class FlightsViewModel @Inject constructor(private val flightRepository: FlightRepository) : ViewModel() {

    private val _flightList = MutableLiveData<Response<List<Flight>>>()
    val flightList: LiveData<Response<List<Flight>>> = _flightList

    private val _status = MutableLiveData<FlightApiStatus>()
    val status: LiveData<FlightApiStatus> = _status

    init {
        getFlightList()
    }

    fun getFlightList() {
        viewModelScope.launch {
            _status.postValue(FlightApiStatus.LOADING)
            try {
                val response = flightRepository.getCountries()
                _flightList.postValue(response)
                _status.postValue(FlightApiStatus.DONE)
            } catch (e: Exception) {
                _status.postValue(FlightApiStatus.ERROR)
            }
        }
    }
}