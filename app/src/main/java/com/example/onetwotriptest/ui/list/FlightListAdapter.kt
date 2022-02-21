package com.example.onetwotriptest.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onetwotriptest.R
import com.example.onetwotriptest.model.Flight
import javax.inject.Inject

class FlightListAdapter @Inject constructor() :
    RecyclerView.Adapter<FlightListAdapter.FlightListViewHolder>() {

    private var flightList = emptyList<Flight>()

    class FlightListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val destinationView: TextView = view.findViewById(R.id.destination_text_view)
        val transferView: TextView = view.findViewById(R.id.transfers_text_view)
        val priceView: TextView = view.findViewById(R.id.price_text_view)
        val cardView: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListViewHolder {
        return FlightListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FlightListViewHolder, position: Int) {
        val currentItem = flightList[position]

        val destinationText = buildString {
            append(currentItem.trips[0].from)
            append(" -> ")
            append(currentItem.trips.last().to)
        }

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

        holder.destinationView.text = destinationText
        holder.priceView.text = priceText
        holder.transferView.text = transfersText

        holder.cardView.setOnClickListener {
            val action =
                FlightListFragmentDirections.actionFlightListFragmentToFlightInfoFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = flightList.size

    fun setData(newList: List<Flight>) {
        flightList = newList
        notifyDataSetChanged()
    }
}