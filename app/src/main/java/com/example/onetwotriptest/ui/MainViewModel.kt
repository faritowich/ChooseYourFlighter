package com.example.onetwotriptest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onetwotriptest.model.Flight
import com.example.onetwotriptest.repositories.FlightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

enum class FlightApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FlightRepository) : ViewModel() {

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