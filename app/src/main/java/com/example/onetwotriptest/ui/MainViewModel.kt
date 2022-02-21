package com.example.onetwotriptest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onetwotriptest.data.network.FlightsApi
import com.example.onetwotriptest.model.Flight
import com.example.onetwotriptest.repositories.FlightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FlightRepository) : ViewModel() {


    private val _flightList = MutableLiveData<List<Flight>>()
    val flightList: LiveData<List<Flight>> = _flightList

    fun getFlightList() {
        viewModelScope.launch {
            _flightList.value = repository.getCountries().body()
        }
    }
}