package com.example.onetwotriptest.presentation.flightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onetwotriptest.R
import com.example.onetwotriptest.data.model.Flight
import com.example.onetwotriptest.databinding.ListViewItemBinding
import com.example.onetwotriptest.presentation.utils.handleAirportName

class FlightListAdapter : ListAdapter<Flight, FlightListAdapter.FlightListViewHolder>(
    UserItemDiffCallback()
) {

    class FlightListViewHolder(
        private val binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(flight: Flight) {

            var minPrice = flight.prices[0].amount
            flight.prices.forEach {
                if (it.amount < minPrice) {
                    minPrice = it.amount
                }
            }

            val priceText = this.itemView.context.getString(R.string.price_from, minPrice)

            val transfersText = buildString {
                if (flight.trips.size - 1 > 0) {
                    append(
                        this@FlightListViewHolder.itemView.context.getString(
                            R.string.transfer,
                            flight.trips.size - 1
                        )
                    )
                } else {
                    append(R.string.no_transfers)
                }
            }

            binding.apply {
                departureTextView.text = flight.trips[0].from.handleAirportName()
                arrivalTextView.text = flight.trips.last().to.handleAirportName()
                transfersTextView.text = transfersText
                priceTextView.text = priceText
            }
        }
    }

    class UserItemDiffCallback : DiffUtil.ItemCallback<Flight>() {
        override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean =
            oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FlightListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FlightListViewHolder(
            ListViewItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FlightListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            if (item.prices.size > 1) {
                val action =
                    FlightListFragmentDirections.actionFlightListFragmentToChoiceDialogFragment(item)

                holder.itemView.findNavController().navigate(action)
            } else {
                val action =
                    FlightListFragmentDirections.actionFlightListFragmentToFlightInfoFragment(
                        item,
                        0
                    )
                holder.itemView.findNavController().navigate(action)
            }
        }
    }
}