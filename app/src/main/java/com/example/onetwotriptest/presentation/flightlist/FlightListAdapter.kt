package com.example.onetwotriptest.presentation.flightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.onetwotriptest.data.model.Flight
import com.example.onetwotriptest.databinding.ListViewItemBinding
import com.example.onetwotriptest.presentation.utils.HandleAirportName
import com.example.onetwotriptest.presentation.utils.handleAirportName
import kotlinx.coroutines.handleCoroutineException

class FlightListAdapter:
    RecyclerView.Adapter<FlightListAdapter.FlightListViewHolder>() {

    private var flightList = emptyList<Flight>()

    class FlightListViewHolder(binding: ListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val departureView: TextView = binding.departureTextView
        val arrivalView: TextView = binding.arrivalTextView
        val transferView: TextView = binding.transfersTextView
        val priceView: TextView = binding.priceTextView
        val cardView: CardView = binding.cardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FlightListViewHolder(
            ListViewItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FlightListViewHolder, position: Int) {
        val currentItem = flightList[position]

        var minPrice = currentItem.prices[0].amount
        currentItem.prices.forEach {
            if (it.amount < minPrice) {
                minPrice = it.amount
            }
        }
        val priceText = "От ${minPrice} р."

        val transfersText = buildString {
            if (currentItem.trips.size - 1 > 0) {
                append(currentItem.trips.size - 1).append(" пересадка")
            } else {
                append("Без пересадок")
            }
        }

        holder.apply {
            departureView.text = currentItem.trips[0].from.handleAirportName()
            arrivalView.text = currentItem.trips.last().to.handleAirportName()
            priceView.text = priceText
            transferView.text = transfersText
        }

        holder.cardView.setOnClickListener {
            if (currentItem.prices.size > 1) {
                val action =
                    FlightListFragmentDirections.actionFlightListFragmentToChoiceDialogFragment(currentItem)

                holder.itemView.findNavController().navigate(action)
            } else {
                val action =
                    FlightListFragmentDirections.actionFlightListFragmentToFlightInfoFragment(
                        currentItem,
                        0
                    )
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = flightList.size

    fun setData(newList: List<Flight>) {
        flightList = newList
        notifyDataSetChanged()
    }
}