package com.example.onetwotriptest.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onetwotriptest.model.Flight
import javax.inject.Inject

class FlightListAdapter @Inject constructor(): RecyclerView.Adapter<FlightListAdapter.FlightListViewHolder>() {

    private val flightList = emptyList<Flight>()

    class FlightListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FlightListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}